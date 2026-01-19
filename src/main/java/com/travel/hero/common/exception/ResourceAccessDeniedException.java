package com.travel.hero.common.exception;

public class ResourceAccessDeniedException extends BusinessException {

    public ResourceAccessDeniedException(String message) {
        super(message);
    }

    public ResourceAccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}
