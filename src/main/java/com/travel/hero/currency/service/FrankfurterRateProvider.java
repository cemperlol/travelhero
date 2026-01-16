package com.travel.hero.currency.service;

import com.travel.hero.currency.client.FrankfurterClient;
import com.travel.hero.currency.enumeration.CurrencyCode;
import com.travel.hero.currency.exception.CurrencyConversionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FrankfurterRateProvider implements CurrencyRateProvider {

    private final FrankfurterClient frankfurterClient;

    @Override
    public BigDecimal getLatestRate(CurrencyCode fromCurrency, CurrencyCode toCurrency) {
        return Optional
                .ofNullable(frankfurterClient.fetchLatestRates(
                                        fromCurrency,
                                        toCurrency
                                )
                                .rates()
                                .get(toCurrency.name())
                ).orElseThrow(() -> new CurrencyConversionException("Rate not found"));
    }

    @Override
    public BigDecimal getRatesAtDate(CurrencyCode fromCurrency, CurrencyCode toCurrency, LocalDate date) {
        return Optional
                .ofNullable(frankfurterClient.fetchRatesAtDate(
                        fromCurrency,
                        toCurrency,
                        date
                )
                        .rates()
                        .get(toCurrency.name())
                ).orElseThrow(() -> new CurrencyConversionException("Rate not found"));
    }
}
