package com.travel.hero.common.exception.handler;

import com.travel.hero.attachment.exception.AttachmentNotFoundException;
import com.travel.hero.common.exception.AccessDeniedException;
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
                e.getMessage()
        );
    }

    @ExceptionHandler(AttachmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleAttachmentNotFound(AttachmentNotFoundException e) {
        return ErrorResponse.create(
                e,
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAttachmentNotFound(AccessDeniedException e) {
        return ErrorResponse.create(
                e,
                HttpStatus.FORBIDDEN,
                e.getMessage()
        );
    }

}
