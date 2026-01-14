package com.travel.hero.attachment.controller;

import com.travel.hero.attachment.dto.AttachmentContent;
import com.travel.hero.attachment.service.DefaultAttachmentService;
import com.travel.hero.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
@RequestMapping("/attachments")
@RequiredArgsConstructor
public class AttachmentController {

    private final DefaultAttachmentService attachmentService;

    @GetMapping("/{id}/content")
    public ResponseEntity<Resource> getContent(
            @PathVariable Long id,
            @AuthenticationPrincipal User currentUser
    ) {
        AttachmentContent content = attachmentService.get(id, currentUser);

        InputStreamResource resource = new InputStreamResource(content.content());

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + content.filename() + "\"")
                .contentType(MediaType.parseMediaType(content.contentType()))
                .body(resource);
    }
}
