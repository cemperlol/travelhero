package com.travel.hero.file.exception;

import com.travel.hero.common.exception.BusinessException;

public class FileIsEmptyException extends BusinessException {
    public FileIsEmptyException(String message) {
        super(message);
    }

  public FileIsEmptyException(String message, Throwable cause) {
    super(message);
  }
}
