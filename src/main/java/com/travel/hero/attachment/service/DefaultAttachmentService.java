package com.travel.hero.attachment.service;

import com.travel.hero.attachment.dto.AttachmentContent;
import com.travel.hero.attachment.dto.AttachmentMetadataResponse;
import com.travel.hero.attachment.dto.CreateAttachmentCommand;
import com.travel.hero.attachment.exception.AttachmentNotFoundException;
import com.travel.hero.attachment.exception.FailedToLoadAttachmentException;
import com.travel.hero.attachment.model.Attachment;
import com.travel.hero.attachment.repository.AttachmentRepository;
import com.travel.hero.common.exception.AccessDeniedException;
import com.travel.hero.file.exception.FileIsEmptyException;
import com.travel.hero.file.exception.FileIsTooBigException;
import com.travel.hero.file.service.FileStorageService;
import com.travel.hero.trip.exception.TripNotFoundException;
import com.travel.hero.trip.model.Trip;
import com.travel.hero.trip.repository.TripRepository;
import com.travel.hero.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultAttachmentService implements AttachmentService {

    private final TripRepository tripRepository;
    private final AttachmentRepository attachmentRepository;
    private final FileStorageService storageService;

    @Override
    public AttachmentMetadataResponse create(
            CreateAttachmentCommand command,
            User currentUser
    ) {
        validateFileSize(command.file());

        Trip trip = findTrip(command.tripId());
        validateUserOwnsTrip(trip, currentUser);

        String storageKey;
        try (InputStream is = command.file().getInputStream()) {
            storageKey = storageService.store(is, command.contentType()).storageKey();
        } catch (IOException e) {
            throw new FailedToLoadAttachmentException("Failed to store attachment");
        }

        Attachment attachment = Attachment.create(
                trip,
                command.originalFilename(),
                command.contentType(),
                command.size(),
                storageKey,
                command.type()
        );

        attachmentRepository.save(attachment);

        return new AttachmentMetadataResponse(
                attachment.getId(),
                attachment.getFilename(),
                attachment.getContentType(),
                attachment.getSize(),
                attachment.getType(),
                attachment.getUploadedAt().toInstant(ZoneOffset.UTC)
        );
    }

    @Override
    @Transactional(readOnly = true)
    public AttachmentContent getContent(
            Long tripId,
            Long attachmentId,
            User currentUser
    ) {
        Attachment attachment = attachmentRepository
                .findByIdAndTripId(attachmentId, tripId)
                .orElseThrow(() ->
                        new AttachmentNotFoundException("No attachment with such attachment id and trip id"));

        validateUserOwnsAttachment(attachment, currentUser);

        InputStream stream = storageService.load(attachment.getStorageKey());

        return new AttachmentContent(
                stream,
                attachment.getFilename(),
                attachment.getContentType()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttachmentMetadataResponse> getAttachments(
            Long tripId,
            User currentUser
    ) {
        Trip trip = findTrip(tripId);

        validateUserOwnsTrip(trip, currentUser);

        return findAttachmentsMetadataAndValidate(trip);
    }

    @Override
    @Transactional
    public void delete(
            Long tripId,
            Long attachmentId,
            User currentUser
    ) {
        Attachment attachment = findAttachmentByIdAndTripId(attachmentId, tripId);

        validateUserOwnsAttachment(attachment, currentUser);

        attachmentRepository.delete(attachment);
    }

    private void validateUserOwnsAttachment(Attachment attachment, User currentUser) {
        if (!attachment.getTrip().getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Access denied");
        }
    }

    private void validateUserOwnsTrip(Trip trip, User currentUser) {
        if (!trip.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Access denied");
        }
    }

    private Trip findTrip(Long tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(() -> new TripNotFoundException("Trip not found"));
    }

    private Attachment findAttachmentByIdAndTripId(Long attachmentId, Long tripId) {
        return attachmentRepository.findByIdAndTripId(attachmentId, tripId)
                .orElseThrow(() -> new AttachmentNotFoundException("There is no such attachment"));
    }

    private void validateFileSize(MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileIsEmptyException("File is empty");
        }

        if (file.getSize() > 10 * 1024 * 1024) {
            throw new FileIsTooBigException("File is too big");
        }
    }

    private List<AttachmentMetadataResponse> findAttachmentsMetadataAndValidate(Trip trip) {
        List<AttachmentMetadataResponse> results = trip.getAttachments()
                .stream()
                .map(a -> new AttachmentMetadataResponse(
                                a.getId(),
                                a.getFilename(),
                                a.getContentType(),
                                a.getSize(),
                                a.getType(),
                                a.getUploadedAt().toInstant(ZoneOffset.UTC)
                        )
                )
                .toList();

        if (results.isEmpty()) {
            throw new AttachmentNotFoundException("Attachments not found");
        }

        return results;
    }
}
