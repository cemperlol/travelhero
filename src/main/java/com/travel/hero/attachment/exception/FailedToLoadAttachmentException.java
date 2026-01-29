package com.travel.hero.attachment.exception;

import com.travel.hero.common.exception.BusinessException;

import java.io.IOException;

public class FailedToLoadAttachmentException extends BusinessException {
    public FailedToLoadAttachmentException(String message) {
        super(message);
    }
    public FailedToLoadAttachmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
