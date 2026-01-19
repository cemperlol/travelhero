package com.travel.hero.file.exception;

import com.travel.hero.common.exception.BusinessException;

public class FileStorageException extends BusinessException {

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
