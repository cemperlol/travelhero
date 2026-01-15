package com.travel.hero.file.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Saved file data")
public record StoredFile (

        @Schema(
                description = "File location",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "/data"
        )
        String storageKey,

        @Schema(
                description = "File size",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "1223"
        )
        Long size
) {}