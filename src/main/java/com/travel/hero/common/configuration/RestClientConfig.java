package com.travel.hero.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient frankfurterRestClient() {
        return RestClient.builder()
                .baseUrl("https://api.frankfurter.app")
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
