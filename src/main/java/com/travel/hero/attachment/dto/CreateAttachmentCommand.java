package com.travel.hero.attachment.dto;

import com.travel.hero.attachment.enumeration.AttachmentType;
import org.springframework.web.multipart.MultipartFile;

public record CreateAttachmentCommand(
        String originalFilename,
        String contentType,
        Long size,
        AttachmentType type,
        Long tripId,
        MultipartFile file
) {}
