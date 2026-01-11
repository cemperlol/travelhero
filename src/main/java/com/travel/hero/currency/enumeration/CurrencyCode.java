package com.travel.hero.currency.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CurrencyCode {
    USD("United States Dollar"),
    EUR("Euro"),
    RUB("Russian Ruble");

    private final String currencyFullName;
}
