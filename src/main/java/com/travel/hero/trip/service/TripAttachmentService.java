package com.travel.hero.trip.service;

import org.springframework.web.multipart.MultipartFile;

public interface TripAttachmentService {

    void addAttachment(
            Long tripId,
            MultipartFile file
    );
}
