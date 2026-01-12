package com.travel.hero.currency.service;

import com.travel.hero.currency.enumeration.CurrencyCode;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CurrencyRateProvider {

    BigDecimal getLatestRate(
            CurrencyCode fromCurrency,
            CurrencyCode toCurrency
    );

    BigDecimal getRatesAtDate(
            CurrencyCode fromCurrency,
            CurrencyCode toCurrency,
            LocalDate date
    );
}
