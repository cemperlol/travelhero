package com.travel.hero.trip.controller;

import com.travel.hero.trip.dto.TripResponse;
import com.travel.hero.trip.service.TripService;
import com.travel.hero.user.model.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Trips"
)
@RestController
@RequestMapping("/api/v1/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @GetMapping("/{id}")
    ResponseEntity<TripResponse> getTrip(
            @PathVariable Long id,
            @AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(tripService.getTrip(id, currentUser));
    }
}
