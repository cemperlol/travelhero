package com.travel.hero.attachment.dto;

import com.travel.hero.attachment.enumeration.AttachmentType;
import com.travel.hero.attachment.model.Attachment;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.InputStream;
import java.time.LocalDateTime;

@Schema(description = "Metadata and file content for network transfer")
public record AttachmentMetadataResponse(

        @Schema(
                description = "Attachment id",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Binary file content as a stream",
                example = "InputStream (not serialized in JSON"
        )
        InputStream content,

        @Schema(
                description = "Original file name with extension",
                example = "report_2024.pdf"
        )
        String filename,

        @Schema(
                description = "MIME content type",
                example = "image/PNG"
        )
        String contentType,

        @Schema(
                description = "Size of attachment",
                example = "1024"
        )
        Long size,

        @Schema(
                description = "Content type",
                example = "IMAGE"
        )
        AttachmentType type,

        @Schema(
                description = "Upload time",
                example = "2026-01-27T18:04:27.792280600"
        )
        LocalDateTime uploadedAt
) {}
