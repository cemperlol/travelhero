package com.travel.hero.trip.dto;

import com.travel.hero.user.model.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateTripRequest(
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal budget,
        String color,
        User user
) { }
