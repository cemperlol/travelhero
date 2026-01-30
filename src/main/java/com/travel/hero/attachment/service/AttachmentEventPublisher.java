package com.travel.hero.attachment.service;

import com.travel.hero.attachment.dto.AttachmentUploadedEvent;

public interface AttachmentEventPublisher {

    void attachmentUploaded(AttachmentUploadedEvent event);
}
