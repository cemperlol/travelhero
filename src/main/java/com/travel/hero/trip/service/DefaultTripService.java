package com.travel.hero.trip.service;

import com.travel.hero.common.exception.AccessDeniedException;
import com.travel.hero.trip.dto.CreateTripRequest;
import com.travel.hero.trip.exception.IncorrectTripDuration;
import com.travel.hero.trip.exception.TripNotFoundException;
import com.travel.hero.trip.dto.TripResponse;
import com.travel.hero.trip.model.Trip;
import com.travel.hero.trip.model.TripDates;
import com.travel.hero.trip.repository.TripRepository;
import com.travel.hero.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultTripService implements TripService {

    private final TripRepository tripRepository;

    @Override
    @Transactional
    public TripResponse create(CreateTripRequest request, User user) {
        if (request.endDate().isBefore(request.startDate())) {
            throw new IncorrectTripDuration("End date must be after start date");
        }

        Trip trip = Trip.create(
                request.name(),
                request.description(),
                new TripDates(request.startDate(), request.endDate()),
                request.budget(),
                request.color(),
                user
        );

        trip = tripRepository.save(trip);

        return mapToTripResponse(trip);
    }

    @Override
    @Transactional(readOnly = true)
    public TripResponse get(Long tripId, User currentUser) {
        Trip trip = findTrip(tripId);

        validateTripAccess(trip, currentUser);

        return mapToTripResponse(trip);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TripResponse> getAllByUserId(UUID userId) {
        return findAllByUserId(userId).stream()
                .map(this::mapToTripResponse)
                .toList();
    }

    @Override
    public void delete(Long tripId, User currentUser) {
        Trip trip = findTrip(tripId);

        validateTripAccess(trip, currentUser);

        tripRepository.delete(trip);
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
                        String.format("There is no trip with id %d", tripId)
                ));
    }

    private List<Trip> findAllByUserId(UUID userId) {
        return tripRepository.findAllByUserId(userId);
    }

    private TripResponse mapToTripResponse(Trip trip) {
        return TripResponse.builder()
                .id(trip.getId())
                .name(trip.getName())
                .description(trip.getDescription())
                .status(trip.getStatus())
                .dates(trip.getDates())
                .budget(trip.getBudget())
                .color(trip.getColor())
                .createdAt(trip.getCreatedAt())
                .updatedAt(trip.getUpdatedAt())
                .build();
    }
}
