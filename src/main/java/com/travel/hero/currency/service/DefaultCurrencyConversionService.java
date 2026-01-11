package com.travel.hero.currency.service;

import com.travel.hero.currency.client.FrankfurterClient;
import com.travel.hero.currency.dto.FrankfurterResponse;
import com.travel.hero.currency.enumeration.CurrencyCode;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class DefaultCurrencyConversionService implements CurrencyConversionService {

    private final FrankfurterClient frankfurterClient;

    @Override
    @Cacheable(value = "currencyConversions",
                key = "{#amount, #fromCurrency, #toCurrency}")
    public BigDecimal convert(
            BigDecimal amount,
            CurrencyCode fromCurrency,
            CurrencyCode toCurrency
    ) {
        LocalDate utcDate = LocalDate.now(ZoneOffset.UTC);

        FrankfurterResponse response = frankfurterClient
                .fetchLatestRates(fromCurrency, toCurrency, utcDate)
                .block();

        BigDecimal rate = response.getRates().get(toCurrency.name());

        return amount.multiply(rate);
    }
}
