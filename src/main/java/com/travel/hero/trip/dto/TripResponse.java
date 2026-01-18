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
                example = "167"
        )
        Long id,

        @Schema(
                description = "Trip name",
                example = "Trip to Germany."
        )
        String name,

        @Schema(
                description = "Trip description",
                example = "Amazing trip to Germany."
        )
        String description,

        @Schema(
                description = "Trip status",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "DREAM",
                allowableValues = {"DREAM", "PLANNING", "BOOKED", "IN_PROGRESS", "COMPLETED", "ARCHIVED"}
        )
        TripStatus status,

        @Schema(
                description = "Trip dates (inclusive)"
        )
        TripDates dates,

        @Schema(
                description = "Trip budget",
                example = "169900"
        )
        BigDecimal budget,

        @Schema(
                description = "Trip color in HEX format",
                example = "#FFFFFF",
                pattern = "^#([A-Fa-f0-9]{6})$"
        )
        String color,

        @Schema(
                description = "date and time of trip creation in UTC",
                example = "2025-09-12T18:56:31Z"
        )
        LocalDateTime createdAt,

        @Schema(
                description = "date and time of trip creation in UTC",
                example = "2025-09-13T12:31:56Z"
        )
        LocalDateTime updatedAt
) {}
