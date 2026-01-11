package com.travel.hero.currency.client;

import com.travel.hero.currency.dto.FrankfurterResponse;
import com.travel.hero.currency.enumeration.CurrencyCode;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.ZoneOffset;

@Component
@RequiredArgsConstructor
public class FrankfurterClient {

    private static final String BASE_URL = "https://api.frankfurter.app";

    private final RestClient restClient;

    @Cacheable(value = "currencyConversions",
            key = "{#baseCurrency, #targetCurrency, #date}")
    public FrankfurterResponse fetchLatestRates(
            CurrencyCode baseCurrency, 
            CurrencyCode targetCurrency,
            LocalDate date
    ) {
        String endpoint = date.isEqual(LocalDate.now(ZoneOffset.UTC))
            ? "/latest"
            : "/" + date;

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(endpoint)
                        .queryParam("from", baseCurrency.name())
                        .queryParam("to", targetCurrency.name())
                        .build())
                .retrieve()
                .body(FrankfurterResponse.class);
    }
}
