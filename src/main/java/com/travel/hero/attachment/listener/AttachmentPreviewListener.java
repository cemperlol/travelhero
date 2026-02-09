package com.travel.hero.attachment.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AttachmentPreviewListener {

    @KafkaListener(
            topics = "attachment.uploaded",
            groupId = "attachment-preview"
    )
    public void handle(String event) {
        System.err.println("Attachment uploaded" + event);
    }
}
