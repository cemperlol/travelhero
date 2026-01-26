package com.travel.hero.user.exception;

import com.travel.hero.common.exception.BusinessException;

public class EmailAlreadyExistsException extends BusinessException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }

  public EmailAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }
}
