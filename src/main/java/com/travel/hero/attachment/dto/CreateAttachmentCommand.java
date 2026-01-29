package com.travel.hero.attachment.dto;

import com.travel.hero.attachment.enumeration.AttachmentType;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public record CreateAttachmentCommand(
        String originalFilename,
        String contentType,
        Long size,
        AttachmentType type,
        Long tripId,
        MultipartFile content
) {}
