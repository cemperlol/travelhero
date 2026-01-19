package com.travel.hero.common.exception.handler;

import com.travel.hero.currency.exception.CurrencyConversionException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order
public class KanbanExceptionHandler {

    @ExceptionHandler(CurrencyConversionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCurrencyConversionError(CurrencyConversionException e) {
        return ErrorResponse.create(
                e,
                HttpStatus.BAD_REQUEST,
                "CURRENCY_NOT_SUPPORTED: " + e.getMessage()
        );
    }
}
