package com.travel.hero.trip.service;

import com.travel.hero.attachment.enumeration.AttachmentType;
import com.travel.hero.file.dto.StoredFile;
import com.travel.hero.file.exception.FileStorageException;
import com.travel.hero.file.service.FileStorageService;
import com.travel.hero.trip.exception.TripNotFoundException;
import com.travel.hero.trip.model.Trip;
import com.travel.hero.trip.repository.TripRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
                .orElseThrow(() ->
                        new TripNotFoundException("There is no trip with such id" + file.getOriginalFilename()));

        StoredFile storedFile;
        try {
            storedFile = storageService.store(
                    file.getInputStream(),
                    file.getOriginalFilename(),
                    file.getContentType());
        } catch (IOException e) {
            throw new FileStorageException(
                    "Failed to read uploaded file: " + file.getOriginalFilename(),
                    e.getCause()
            );
        }

        trip.addAttachment(
                file.getOriginalFilename(),
                file.getContentType(),
                storedFile.size(),
                storedFile.storageKey(),
                AttachmentType.IMAGE
        );
    }
}
