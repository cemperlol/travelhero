package com.travel.hero.attachment.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.InputStream;

@Schema(description = "Metadata and file content for network transfer")
public record AttachmentContent(

        @Schema(
                description = "Binary file content as a stream",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "InputStream (not serialized in JSON"
        )
        InputStream content,

        @Schema(
                description = "Original file name with extension",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "report_2024.pdf"
        )
        String filename,

        @Schema(
                description = "MIME content type",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "image/PNG"
        )
        String contentType
) {}
