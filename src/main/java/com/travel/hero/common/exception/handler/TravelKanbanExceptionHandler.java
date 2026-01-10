package com.travel.hero.common.exception.handler;

import com.travel.hero.currency.exception.CurrencyConversionException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class TravelKanbanExceptionHandler {

    @ExceptionHandler(CurrencyConversionException.class)
    public ErrorResponse handleCurrencyConversionError(CurrencyConversionException e) {
        return buildCurrencyError(e);
    }
}
