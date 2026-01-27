package com.travel.hero.attachment.dto;

import com.travel.hero.attachment.enumeration.AttachmentType;

public record CreateAttachmentRequest(
        String filename,
        String contentType,
        String fileExtension,
        Long size,
        AttachmentType attachmentType,
        Long tripId
) {}
