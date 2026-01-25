package com.travel.hero.trip.service;

import com.travel.hero.trip.dto.CreateTripRequest;
import com.travel.hero.trip.dto.TripResponse;
import com.travel.hero.user.model.User;

public interface TripService {

    TripResponse create(
            CreateTripRequest request,
            User user
    );

    TripResponse get(
            Long tripId,
            User currentUser
    );

    void delete(
            Long tripId,
            User currentUser
    );
}
