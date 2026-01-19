package com.travel.hero.file.exception;

public class FileDeletionException extends FileStorageException {
    public FileDeletionException(String message) {
        super(message);
    }

    public FileDeletionException(String message, Throwable cause) {
        super(message, cause);
    }
}
