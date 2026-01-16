package com.travel.hero.currency.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "Currency code.")
@RequiredArgsConstructor
@Getter
public enum CurrencyCode {

    @Schema(description = "United States Dollar.")
    USD("United States Dollar"),

    @Schema(description = "Euro.")
    EUR("Euro"),

    @Schema(description = "Russian ruble.")
    RUB("Russian Ruble");

    private final String currencyFullName;
}
