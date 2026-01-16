package com.travel.hero.attachment.controller;

import com.travel.hero.attachment.dto.AttachmentContent;
import com.travel.hero.attachment.service.DefaultAttachmentService;
import com.travel.hero.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.accept.MediaTypeFileExtensionResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@Tag(
        name = "Attachments",
        description = "Working with attachments (files, documents, etc.)"
)
@RestController
@RequestMapping("/api/v1/attachments")
@RequiredArgsConstructor
public class AttachmentController {

    private final DefaultAttachmentService attachmentService;
    private final MediaTypeFileExtensionResolver extensionResolver;

    @Operation(
            summary = "Get attachment content",
            description = """
                    Returns content of the attachment.
                    
                    Requires authentication.
                    The file is returned inline with its original name and MIME type.
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "File successfully received",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                            schema = @Schema(type = "string", format = "binary")
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "User is not authenticated"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "No access to attachment"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Attachment not found"
            )
    })
    @GetMapping("/{id}/content")
    public ResponseEntity<Resource> getContent(
            @PathVariable Long id,
            @AuthenticationPrincipal User currentUser
    ) {
        AttachmentContent content = attachmentService.get(id, currentUser);

        InputStreamResource resource = new InputStreamResource(content.content());

        return ResponseEntity
                .ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + content.filename() + "\""
                )
                .contentType(MediaType.parseMediaType(content.contentType()))
                .body(resource);
    }
}
