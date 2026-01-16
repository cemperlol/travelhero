package com.travel.hero.attachment.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Attachment type")
public enum AttachmentType {

    @Schema(
            description = "Image attachment.",
            example = "png, jpg, etc."
    )
    IMAGE,

    @Schema(
            description = "Video attachment.",
            example = "mp4, mov, etc."
    )
    VIDEO,

    @Schema(
            description = "Document attachment.",
            example = "json, pdf, etc."
    )
    DOCUMENT,

    @Schema(
            description = "Other attachment.",
            example = "mp3, stl, etc."
    )
    OTHER;
}
