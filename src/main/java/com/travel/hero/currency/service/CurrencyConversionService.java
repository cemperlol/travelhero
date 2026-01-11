package com.travel.hero.currency.service;

import com.travel.hero.currency.dto.CurrencyConversionResponse;
import com.travel.hero.currency.enumeration.CurrencyCode;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CurrencyConversionService {

    CurrencyConversionResponse convert(
            BigDecimal amount,
            CurrencyCode fromCurrency,
            CurrencyCode toCurrency
    );
}
