package com.travel.hero.trip.model;

import com.travel.hero.trip.exception.IncorrectTripDuration;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Embeddable
@Data
public class TripDates {

    @Schema(
            description = "Start date of trip",
            example = "2026-08-14"
    )
    private LocalDate startDate;

    @Schema(
            description = "End date of trip",
            example = "2026-08-21"
    )
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
