package com.travel.hero.currency.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Schema(description = "Frankfurter API response with currency rates and conversion date")
public record FrankfurterResponse (

        @Schema(
                description = "Currency from which to convert",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "USD"
        )
        String base,

        @Schema(
                description = "Date of conversion",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "2024.08.14"
        )
        LocalDate date,

        @Schema(
                description = "Conversion rates for various currencies",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                example = "USD, 1.09423"
        )
        Map<String, BigDecimal> rates
) {}
