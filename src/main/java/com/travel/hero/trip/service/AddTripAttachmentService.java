package com.travel.hero.trip.service;

import com.travel.hero.file.service.FileStorageService;
import com.travel.hero.trip.TripNotFoundException;
import com.travel.hero.trip.model.Trip;
import com.travel.hero.trip.repository.TripRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddTripAttachmentService implements TripAttachmentService {

    public final TripRepository tripRepository;
    private final FileStorageService storageService;

    @Override
    @Transactional
    public void addAttachment(
            Long tripId,
            MultipartFile file) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new TripNotFoundException("There is no trip with such id"));

        StorageResult result =
    }
}
