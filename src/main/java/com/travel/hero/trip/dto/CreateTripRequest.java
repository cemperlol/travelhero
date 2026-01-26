package com.travel.hero.trip.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "Trip creation information")
public record CreateTripRequest(

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
                description = "Start trip date",
                example = "2026/01/26"
        )
        LocalDate startDate,

        @Schema(
                description = "End trip date",
                example = "2026/02/02"
        )
        LocalDate endDate,

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
        String color
) { }
