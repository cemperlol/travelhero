package com.travel.hero.currency.exception;

import com.travel.hero.common.exception.BusinessException;

public class CurrencyConversionException extends BusinessException {

    public CurrencyConversionException(String message) {
        super(message);
    }
}
