package com.travel.hero.file.exception;

public class FileIsTooBigException extends RuntimeException {
    public FileIsTooBigException(String message) {
        super(message);
    }

    public FileIsTooBigException(String message, Throwable cause) {
        super(message, cause);
    }
}
