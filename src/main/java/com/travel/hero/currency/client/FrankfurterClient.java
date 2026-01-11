package com.travel.hero.currency.client;

import com.travel.hero.currency.dto.FrankfurterResponse;
import com.travel.hero.currency.enumeration.CurrencyCode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FrankfurterClient {

    private static final String BASE_URL = "https://api.frankfurter.app";

    private final WebClient webClient;

    public FrankfurterClient() {
        this.webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader("Accept", "application/json")
                .build();
    }

    public Mono<FrankfurterResponse> fetchLatestRates(
            CurrencyCode baseCurrency, 
            CurrencyCode targetCurrency,
            LocalDate date
    ) {
        String endpoint = date.isEqual(LocalDate.now(ZoneOffset.UTC))
            ? "/latest"
            : "/" + date;

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(endpoint)
                        .queryParam("from", baseCurrency.name())
                        .queryParam("to", targetCurrency.name())
                        .build())
                .retrieve()
                .bodyToMono(FrankfurterResponse.class);
    }

    @Scheduled(cron = "0 0 * * * *")
    public void updateRates() {
        fetchLatestRates(CurrencyCode.EUR, CurrencyCode.RUB, LocalDate.now());
    }
}
