package com.travel.hero.attachment.listener;

import com.travel.hero.attachment.dto.AttachmentUploadedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AttachmentPreviewListener {

    @KafkaListener(
            topics = "attachment.uploaded",
            groupId = "attachment-preview"
    )
    public void handle(AttachmentUploadedEvent event) {
        System.err.println("Attachment uploaded" + event);
    }
}
