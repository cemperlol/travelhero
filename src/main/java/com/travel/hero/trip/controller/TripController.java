package com.travel.hero.trip.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Trips"
)
@RestController
@RequestMapping("/api/v1/trips")
@RequiredArgsConstructor
public class TripController {

}
