package com.travel.hero.trip.repository;

import com.travel.hero.trip.model.Trip;
import com.travel.hero.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findAllByUserId(UUID userId);

    UUID user(User user);
}
