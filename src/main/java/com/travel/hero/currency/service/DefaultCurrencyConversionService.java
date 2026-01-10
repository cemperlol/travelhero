package com.travel.hero.currency.service;

import com.travel.hero.currency.enumeration.CurrencyCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DefaultCurrencyConversionService implements CurrencyConversionService {

    @Override
    public BigDecimal convert(BigDecimal amount, CurrencyCode fromCurrency, CurrencyCode toCurrency, LocalDate date) {
        return null;
    }

    @ExceptionHandler(Exception.class)
    private void handle() {

    }
}
