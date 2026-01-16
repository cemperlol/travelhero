package com.travel.hero.currency.controller;

import com.travel.hero.currency.enumeration.CurrencyCode;
import com.travel.hero.currency.service.CurrencyConversionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Tag(
        name = "Currencies",
        description = "Working with currency (USD, EUR, etc.)"
)
@RestController
@RequestMapping("/api/v1/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyConversionService currencyConversionService;

    @Operation(
            summary = "Exchange currencies",
            description = """
                    Converts one currency into another and returns the conversion result.
                    
                    Works with frankfurter API.
                    Currency returns with conversion of 1 unit.
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "The currency was successfully converted."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Incorrect amount for conversion"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Currency for conversion not found"
            )
    })
    @GetMapping("/exchange")
    public BigDecimal exchangeCurrency(
            @RequestParam BigDecimal amount,
            @RequestParam CurrencyCode fromCurrency,
            @RequestParam CurrencyCode toCurrency
    ) {
        return currencyConversionService.convert(amount, fromCurrency, toCurrency);
    }
}
