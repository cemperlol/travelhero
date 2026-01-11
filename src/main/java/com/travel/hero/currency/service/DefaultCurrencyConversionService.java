package com.travel.hero.currency.service;

import com.travel.hero.currency.client.FrankfurterClient;
import com.travel.hero.currency.dto.FrankfurterResponse;
import com.travel.hero.currency.enumeration.CurrencyCode;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DefaultCurrencyConversionService implements CurrencyConversionService {

    private final FrankfurterClient frankfurterClient;

    @Override
    @Cacheable(value = "currencyConversions",
                key = "{#amount, #fromCurrency, #toCurrency, #date}")
    public BigDecimal convert(
            BigDecimal amount,
            CurrencyCode fromCurrency,
            CurrencyCode toCurrency,
            LocalDate date
    ) {
        FrankfurterResponse response =

        return null;
    }
}
