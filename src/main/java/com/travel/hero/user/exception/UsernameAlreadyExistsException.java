package com.travel.hero.user.exception;

import com.travel.hero.common.exception.BusinessException;

public class UsernameAlreadyExistsException extends BusinessException {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }

    public UsernameAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
