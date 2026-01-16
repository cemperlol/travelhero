package com.travel.hero.attachment.service;

import com.travel.hero.attachment.dto.AttachmentContent;
import com.travel.hero.attachment.exception.AttachmentNotFoundException;
import com.travel.hero.attachment.model.Attachment;
import com.travel.hero.attachment.repository.AttachmentRepository;
import com.travel.hero.common.exception.AccessDeniedException;
import com.travel.hero.file.service.FileStorageService;
import com.travel.hero.trip.model.Trip;
import com.travel.hero.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class DefaultAttachmentService implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final FileStorageService storageService;

    @Override
    @Transactional(readOnly = true)
    public AttachmentContent get(Long attachmentId, User currentUser) {
        Attachment attachment = attachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new AttachmentNotFoundException("There is no such attachment"));

        Trip trip = attachment.getTrip();

        if (!trip.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Access denied");
        }

        InputStream content = storageService.load(attachment.getStorageKey());

        return new AttachmentContent(
                content,
                attachment.getFilename(),
                attachment.getContentType()
        );
    }

    @Override
    @Transactional
    public void delete(Long attachmentId, User currentUser) {
        Attachment attachment = attachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new AttachmentNotFoundException("There is no such attachment"));

        if (!attachment.getTrip().getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Access denied");
        } else {
            attachmentRepository.deleteById(attachmentId);
        }
    }
}
