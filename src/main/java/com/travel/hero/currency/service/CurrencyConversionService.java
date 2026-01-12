package com.travel.hero.currency.service;

import com.travel.hero.currency.enumeration.CurrencyCode;

import java.math.BigDecimal;

public interface CurrencyConversionService {

    BigDecimal convert(
            BigDecimal amount,
            CurrencyCode fromCurrency,
            CurrencyCode toCurrency
    );
}
