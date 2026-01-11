package com.travel.hero.currency.dto;

import com.travel.hero.currency.enumeration.CurrencyCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CurrencyConversionResponse {

    private BigDecimal amount;
    private CurrencyCode fromCurrency;
    private CurrencyCode toCurrency;
    private LocalDate date;
}
