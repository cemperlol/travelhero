package com.travel.hero.common.exception.handler;

import com.travel.hero.attachment.exception.AttachmentNotFoundException;
import com.travel.hero.common.exception.AccessDeniedException;
import com.travel.hero.currency.exception.CurrencyConversionException;
import com.travel.hero.file.exception.FileDeletionException;
import com.travel.hero.file.exception.FileNotFoundException;
import com.travel.hero.file.exception.FileReaderException;
import com.travel.hero.file.exception.FileWriterException;
import com.travel.hero.trip.exception.IncorrectTripDuration;
import com.travel.hero.trip.exception.TripNotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order
public class KanbanExceptionHandler {


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
    public ErrorResponse handleAccessDenied(AccessDeniedException e) {
        return ErrorResponse.create(
                e,
                HttpStatus.FORBIDDEN,
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

    @ExceptionHandler(CurrencyConversionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCurrencyConversionError(CurrencyConversionException e) {
        return ErrorResponse.create(
                e,
                HttpStatus.BAD_REQUEST,
                e.getMessage()
        );
    }

    @ExceptionHandler(FileDeletionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleFileDeletionError(FileDeletionException e) {
        return ErrorResponse.create(
                e,
                HttpStatus.BAD_REQUEST,
                e.getMessage()
        );
    }

    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleFileNotFound(FileNotFoundException e) {
        return ErrorResponse.create(
                e,
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );
    }

    @ExceptionHandler(FileReaderException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleFileReaderError(FileReaderException e) {
        return ErrorResponse.create(
                e,
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage()
        );
    }

    @ExceptionHandler(FileWriterException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleFileWriterError(FileWriterException e) {
        return ErrorResponse.create(
                e,
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage()
        );
    }

    @ExceptionHandler(IncorrectTripDuration.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleFileWriterError(IncorrectTripDuration e) {
        return ErrorResponse.create(
                e,
                HttpStatus.BAD_REQUEST,
                e.getMessage()
        );
    }

    @ExceptionHandler(TripNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleTripNofFound(TripNotFoundException e) {
        return ErrorResponse.create(
                e,
                HttpStatus.BAD_REQUEST,
                e.getMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnexpectedError(Exception e) {
        return ErrorResponse.create(
                e,
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected server error"
        );
    }
}
