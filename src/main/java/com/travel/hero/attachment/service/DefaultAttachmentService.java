package com.travel.hero.attachment.service;

import com.travel.hero.attachment.dto.AttachmentMetadataResponse;
import com.travel.hero.attachment.dto.CreateAttachmentCommand;
import com.travel.hero.attachment.exception.AttachmentNotFoundException;
import com.travel.hero.attachment.model.Attachment;
import com.travel.hero.attachment.repository.AttachmentRepository;
import com.travel.hero.common.exception.AccessDeniedException;
import com.travel.hero.file.service.FileStorageService;
import com.travel.hero.trip.exception.TripNotFoundException;
import com.travel.hero.trip.model.Trip;
import com.travel.hero.trip.repository.TripRepository;
import com.travel.hero.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class DefaultAttachmentService implements AttachmentService {

    private final TripRepository tripRepository;
    private final AttachmentRepository attachmentRepository;
    private final FileStorageService storageService;

    @Override
    public AttachmentMetadataResponse create(
            CreateAttachmentCommand command, User currentUser
    ) {
        Trip trip = tripRepository.findById(command.tripId())
                .orElseThrow(() -> new TripNotFoundException("Trip not found"));

        if (!trip.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Access denied");
        }

        String storageKey = storageService.store(
                command.content(),
                command.contentType()
        );

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
    public AttachmentMetadataResponse get(Long tripId, Long attachmentId, User currentUser) {
        Attachment attachment = attachmentRepository.findByIdAndTripId(attachmentId, tripId)
                .orElseThrow(() -> new AttachmentNotFoundException("There is no such attachment"));

        validateAttachmentAccess(attachment, currentUser);

        try(InputStream content = storageService.load(attachment.getStorageKey())) {

        } catch (IOException e) {

        }

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
    @Transactional
    public void delete(Long tripId, Long attachmentId, User currentUser) {
        Attachment attachment = attachmentRepository.findByIdAndTripId(attachmentId, tripId)
                .orElseThrow(() -> new AttachmentNotFoundException("There is no such attachment"));

        validateAttachmentAccess(attachment, currentUser);

        attachmentRepository.delete(attachment);
    }

    private void validateAttachmentAccess(Attachment attachment, User currentUser) {
        if (!attachment.getTrip().getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Access denied");
        }
    }
}
