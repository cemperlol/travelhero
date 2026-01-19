package com.travel.hero.attachment.exception;

import com.travel.hero.common.exception.BusinessException;

public class AttachmentNotFoundException extends BusinessException {

    public AttachmentNotFoundException(String message) {
        super(message);
    }

    public AttachmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
