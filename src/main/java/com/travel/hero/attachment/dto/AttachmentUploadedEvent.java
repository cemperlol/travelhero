package com.travel.hero.attachment.dto;

public record AttachmentUploadedEvent(
        Long attachmentId,
        Long tripId
) {}
