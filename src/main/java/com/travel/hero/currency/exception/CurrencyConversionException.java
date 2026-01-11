package com.travel.hero.currency.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class CurrencyConversionException extends RuntimeException {

    public CurrencyConversionException(String message) {
        super(message);
    }
}
