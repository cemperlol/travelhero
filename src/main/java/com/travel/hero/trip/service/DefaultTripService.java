package com.travel.hero.trip.service;

import com.travel.hero.common.exception.AccessDeniedException;
import com.travel.hero.trip.TripNotFoundException;
import com.travel.hero.trip.dto.TripResponse;
import com.travel.hero.trip.model.Trip;
import com.travel.hero.trip.repository.TripRepository;
import com.travel.hero.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultTripService implements TripService {

    private final TripRepository tripRepository;

    @Override
    public TripResponse getTrip(Long tripId, User currentUser) {
        Trip trip = findTrip(tripId);

        validateTripAccess(trip, currentUser);

        return TripResponse.builder()
                .id(trip.getId())
                .name(trip.getName())
                .description(trip.getDescription())
                .status(trip.getStatus())
                .dates(trip.getDates())
                .budget(trip.getBudget())
                .createdAt(trip.getCreatedAt())
                .updatedAt(trip.getUpdatedAt())
                .version(trip.getVersion())
                .build();
    }

    @Override
    public void deleteTrip(Long tripId, User currentUser) {
        Trip trip = findTrip(tripId);

        validateTripAccess(trip, currentUser);

        tripRepository.deleteById(tripId);
    }

    private void validateTripAccess(Trip trip, User currentUser) {
        if (!trip.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException(
                    String.format("User '%s' has no access to trip %d",
                            currentUser.getUsername(), trip.getId())
            );
        }
    }

    private Trip findTrip(Long tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(() -> new TripNotFoundException(
                        String.format("There is no trip with id %d", tripId)));
    }
}
