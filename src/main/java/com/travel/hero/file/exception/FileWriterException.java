package com.travel.hero.file.exception;

import com.travel.hero.common.exception.BusinessException;

public class FileWriterException extends FileStorageException {

    public FileWriterException(String message) {
        super(message);
    }

    public FileWriterException(String message, Throwable cause) {
        super(message, cause);
    }
}
