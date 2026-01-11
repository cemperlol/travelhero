package com.travel.hero.currency.client;

import com.travel.hero.currency.dto.FrankfurterResponse;
import com.travel.hero.currency.enumeration.CurrencyCode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.ObjectMapper;

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

    public FrankfurterResponse fetchLatestRates(CurrencyCode base, Set<CurrencyCode> targets) {
        String targetCurrencies = targets.stream()
                .map(CurrencyCode::getCurrencyName)
                .collect(Collectors.joining(","));

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/latest")
                        .queryParam("from", base.name())
                        .queryParam("to", targetCurrencies)
                        .build())
                .retrieve()
                .bodyToMono(FrankfurterResponse.class)
                .block();
    }

    @Scheduled(cron = "0 0 * * * *")
    public void updateRates() {

    }
}
