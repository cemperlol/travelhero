package com.travel.hero.attachment.exception;

import java.io.IOException;

public class FailedToLoadAttachmentException extends IOException {
    public FailedToLoadAttachmentException(String message) {
        super(message);
    }
    public FailedToLoadAttachmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
