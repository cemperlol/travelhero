package com.travel.hero.attachment.service;

public interface AttachmentEventPublisher {

    void attachmentUploaded(Long attachmentId, Long tripId);
}
