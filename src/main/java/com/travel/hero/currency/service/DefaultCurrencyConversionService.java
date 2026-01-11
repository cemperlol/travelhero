package com.travel.hero.currency.service;

import com.travel.hero.currency.client.FrankfurterClient;
import com.travel.hero.currency.dto.CurrencyConversionResponse;
import com.travel.hero.currency.dto.FrankfurterResponse;
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
public class DefaultCurrencyConversionService implements CurrencyConversionService {

    private final FrankfurterClient frankfurterClient;

    @Override
    public CurrencyConversionResponse convert (
            BigDecimal amount,
            CurrencyCode fromCurrency,
            CurrencyCode toCurrency
    ) {
        LocalDate utcDate = LocalDate.now(ZoneOffset.UTC);

        FrankfurterResponse frankfurterResponse = frankfurterClient
                .fetchLatestRates(fromCurrency, toCurrency, utcDate);

        BigDecimal rate = Optional
                .ofNullable(frankfurterResponse.getRates().get(toCurrency.name()))
                .orElseThrow(() -> new CurrencyConversionException("Rate not found"));

        CurrencyConversionResponse response = new CurrencyConversionResponse();
        response.setAmount(amount.multiply(rate));
        response.setFromCurrency(fromCurrency);
        response.setToCurrency(toCurrency);
        response.setDate(utcDate);

        return response;
    }
}
