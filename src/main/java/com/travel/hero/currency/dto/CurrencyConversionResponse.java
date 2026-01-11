package com.travel.hero.currency.dto;

import com.travel.hero.currency.enumeration.CurrencyCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CurrencyConversionResponse {

    private BigDecimal amount;
    private String error = "no error";
    private CurrencyCode fromCurrency;
    private CurrencyCode toCurrency;
}
