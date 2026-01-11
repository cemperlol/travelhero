package com.travel.hero.currency.controller;

import com.travel.hero.currency.dto.CurrencyConversionResponse;
import com.travel.hero.currency.enumeration.CurrencyCode;
import com.travel.hero.currency.service.CurrencyConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyConversionService currencyConversionService;

    @GetMapping("/exchange")
    public ResponseEntity<CurrencyConversionResponse> exchangeCurrency(
            @RequestParam BigDecimal amount,
            @RequestParam CurrencyCode fromCurrency,
            @RequestParam CurrencyCode toCurrency
    ) {
        return ResponseEntity.ok(currencyConversionService.convert(amount, fromCurrency, toCurrency));
    }
}
