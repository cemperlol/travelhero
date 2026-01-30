package com.travel.hero.attachment.service;

import com.travel.hero.attachment.dto.AttachmentUploadedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultAttachmentEventPublisher implements AttachmentEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void attachmentUploaded(Long attachmentId, Long tripId) {
        kafkaTemplate.send(
                "attachment.uploaded",
                new AttachmentUploadedEvent(attachmentId, tripId)
        );
    }
}
