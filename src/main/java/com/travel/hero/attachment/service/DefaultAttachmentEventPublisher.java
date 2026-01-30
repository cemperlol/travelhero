package com.travel.hero.attachment.service;

import com.travel.hero.attachment.dto.AttachmentUploadedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class DefaultAttachmentEventPublisher implements AttachmentEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void attachmentUploaded(AttachmentUploadedEvent event) {
        String jsonMessage = objectMapper.writeValueAsString(event);
        kafkaTemplate.send(
                "attachment.uploaded",
                jsonMessage
        );
    }
}
