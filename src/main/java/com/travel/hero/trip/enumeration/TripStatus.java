package com.travel.hero.trip.enumeration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "Travel status")
@RequiredArgsConstructor
@Getter
public enum TripStatus {

    @Schema(description = "Travel is still just a dream.")
    DREAM("Dream"),

    @Schema(description = "Travel planning has begun.")
    PLANNING("Planning"),

    @Schema(description = "The trip has already been planned and everything necessary has been booked.")
    BOOKED("Booked"),

    @Schema(description = "The journey has begun.")
    IN_PROGRESS("In progress"),

    @Schema(description = "The journey is complete.")
    COMPLETED("Completed"),

    @Schema(description = "The journey is archived.")
    ARCHIVED("In archive");

    private final String displayName;
}
