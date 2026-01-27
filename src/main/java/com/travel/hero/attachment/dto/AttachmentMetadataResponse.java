package com.travel.hero.attachment.dto;

import com.travel.hero.attachment.enumeration.AttachmentType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "Metadata and file content for network transfer")
public record AttachmentMetadataResponse(

        Long id,

        String filename,

        String contentType,

        Long size,

        @Schema(
                description = "Content type",
                example = "IMAGE"
        )
        AttachmentType type,

        Instant uploadedAt
) {}
