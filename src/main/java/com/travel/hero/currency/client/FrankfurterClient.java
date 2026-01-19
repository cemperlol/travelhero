package com.travel.hero.currency.client;

import com.travel.hero.currency.dto.FrankfurterResponse;
import com.travel.hero.currency.enumeration.CurrencyCode;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class FrankfurterClient {

    private final RestClient restClient;

    @Cacheable(value = "currencyRates",
            key = "{#baseCurrency, #targetCurrency}")
    public FrankfurterResponse fetchLatestRates(
            CurrencyCode baseCurrency,
            CurrencyCode targetCurrency
    ) {
        String endpoint = "/latest";

        return restClient.get()
                .uri(uriBuilder ->  uriBuilder
                        .path(endpoint)
                        .queryParam("from", baseCurrency.name())
                        .queryParam("to", targetCurrency.name())
                        .build())
                .retrieve()
                .body(FrankfurterResponse.class);
    }

    @Cacheable(value = "currencyRates",
            key = "{#baseCurrency, #targetCurrency, #date}")
    public FrankfurterResponse fetchRatesAtDate(
            CurrencyCode baseCurrency, 
            CurrencyCode targetCurrency,
            LocalDate date
    ) {
        String endpoint = "/" + date;

        return restClient.get()
                .uri(uriBuilder ->  uriBuilder
                        .path(endpoint)
                        .queryParam("from", baseCurrency.name())
                        .queryParam("to", targetCurrency.name())
                        .build())
                .retrieve()
                .body(FrankfurterResponse.class);
    }
}
