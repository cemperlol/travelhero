package com.travel.hero.trip.exception;

import com.travel.hero.common.exception.BusinessException;

public class IncorrectTripDuration extends BusinessException {

    public IncorrectTripDuration(String message) {
        super(message);
    }
}
