package com.travel.hero.attachment.controller;

import com.travel.hero.attachment.service.DefaultAttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attachments")
@RequiredArgsConstructor
public class AttachmentController {

    private final DefaultAttachmentService attachmentService;

    @GetMapping("/{id}/content")
    public ResponseEntity<Resource> getContent(
            @PathVariable Long id,
            @AuthenticationPrincipal
    )
}
