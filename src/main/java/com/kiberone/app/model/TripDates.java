package com.kiberone.app.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.OptionalInt;

@Embeddable
@Data
public class TripDates {

    private LocalDate startDate;

    private LocalDate endDate;

    public OptionalInt getTripDuration() {
        if (startDate == null
                || endDate == null
                || endDate.isBefore(startDate)) {
            return OptionalInt.empty();
        }

        return OptionalInt.of((int) ChronoUnit.DAYS.between(startDate, endDate) + 1);
    }
}
