package com.travel.hero.trip.service;

import com.travel.hero.trip.dto.CreateTripRequest;
import com.travel.hero.trip.dto.TripResponse;
import com.travel.hero.user.model.User;

import java.util.List;
import java.util.UUID;

public interface TripService {

    TripResponse create(
            CreateTripRequest request,
            User user
    );

    TripResponse get(
            Long tripId,
            User currentUser
    );

    List<TripResponse> getAllByUserId(UUID id);

    void delete(
            Long tripId,
            User currentUser
    );
}
