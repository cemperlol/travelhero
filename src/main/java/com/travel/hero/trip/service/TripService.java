package com.travel.hero.trip.service;

import com.travel.hero.trip.dto.TripResponse;
import com.travel.hero.user.model.User;

public interface TripService {

    TripResponse getTrip(
            Long tripId,
            User currentUser
    );
}
