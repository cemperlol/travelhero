package com.travel.hero.file.exception;

public class FileReaderException extends FileStorageException {

    public FileReaderException(String message) {
        super(message);
    }

    public FileReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
