package com.travel.hero.trip.exception;

import com.travel.hero.common.exception.BusinessException;

public class TripNotFoundException extends BusinessException {
    public TripNotFoundException(String message) {
        super(message);
    }

    public TripNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
