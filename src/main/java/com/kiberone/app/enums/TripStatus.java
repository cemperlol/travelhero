package com.kiberone.app.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TripStatus {

    DREAM("Dream"),
    PLANNING("Planning"),
    BOOKED("Booked"),
    IN_PROGRESS("In progress"),
    COMPLETED("Completed"),
    ARCHIVED("In archive");

    private final String displayName;
}
