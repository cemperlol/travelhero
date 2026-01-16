package com.travel.hero.trip.model;

import com.travel.hero.trip.exception.IncorrectTripDuration;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.OptionalInt;

@Embeddable
@Data
public class TripDates {

    private LocalDate startDate;

    private LocalDate endDate;

    public int getTripDuration() {
        if (startDate == null || endDate == null) {
            throw new IncorrectTripDuration("Both dates must be set");
        }

        if (endDate.isBefore(startDate)) {
            throw new IncorrectTripDuration("End date cannot be before start date");
        }

        return (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }
}
