package com.travel.hero.trip.dto;

import com.travel.hero.trip.enumeration.TripStatus;
import com.travel.hero.trip.model.TripDates;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Trip general information")
@Builder
public record TripResponse(

        @Schema(
                description = "Trip id",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "167"
        )
        Long id,

        @Schema(
                description = "Trip name",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "Trip to Germany."
        )
        String name,

        @Schema(
                description = "Trip description",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "Amazing trip to Germany."
        )
        String description,

        @Schema(
                description = "Trip status",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "DREAM"
        )
        TripStatus status,

        @Schema(
                description = "Trip dates. Note: Duration includes both start and end days.",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "{\"startDate\": \"2026-08-14\", \"endDate\": \"2026-08-21\"}"
        )
        TripDates dates,

        @Schema(
                description = "Trip budget",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "169900"
        )
        BigDecimal budget,

        @Schema(
                description = "date and time of trip creation in UTC",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "2025-09-12 18:56:31"
        )
        LocalDateTime createdAt,

        @Schema(
                description = "date and time of trip creation in UTC",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "2025-09-13 12:31:56"
        )
        LocalDateTime updatedAt,

        @Schema(
                description = "version for optimistic lock",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "1"
        )
        Long version
) {}
