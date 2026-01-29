package com.travel.hero.attachment.repository;

import com.travel.hero.attachment.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    Optional<Attachment> findByIdAndTripId(Long attachmentId, Long tripId);
}
