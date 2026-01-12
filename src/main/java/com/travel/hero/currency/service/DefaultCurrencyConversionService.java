package com.travel.hero.currency.service;

import com.travel.hero.currency.enumeration.CurrencyCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DefaultCurrencyConversionService implements CurrencyConversionService {

    private final CurrencyRateProvider rateProvider;

    @Override
    public BigDecimal convert(
            BigDecimal amount,
            CurrencyCode fromCurrency,
            CurrencyCode toCurrency
    ) {
        BigDecimal rate = rateProvider.getLatestRate(fromCurrency, toCurrency);

        return amount.multiply(rate);
    }
}
