package com.travel.hero.attachment.controller;

import com.travel.hero.attachment.dto.AttachmentContent;
import com.travel.hero.attachment.dto.AttachmentMetadataResponse;
import com.travel.hero.attachment.dto.CreateAttachmentCommand;
import com.travel.hero.attachment.enumeration.AttachmentType;
import com.travel.hero.attachment.service.AttachmentService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(
        name = "Attachments",
        description = "Working with attachments (files, documents, etc.)"
)
@RestController
@RequestMapping("/api/v1/trips/{tripId}/attachments")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @Operation(
            summary = "Post attachment",
            description = """
                    Posts and returns attachment
                    
                    Requires authentication
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Attachment successfully created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid attachment data"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "User is not authenticated"
            )
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AttachmentMetadataResponse> uploadAttachment(
            @PathVariable Long tripId,
            @RequestPart("file") MultipartFile file,
            @RequestPart("type") AttachmentType type,
            @AuthenticationPrincipal User currentUser
    ) {
        if (file.isEmpty()) {
            throw new
        }

        CreateAttachmentCommand command = new CreateAttachmentCommand(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getSize(),
                type,
                tripId,
                file
        );

        AttachmentMetadataResponse response = attachmentService.create(command, currentUser);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

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
    @GetMapping("/{attachmentId}/content")
    public ResponseEntity<Resource> getContent(
            @PathVariable Long tripId,
            @PathVariable Long attachmentId,
            @AuthenticationPrincipal User currentUser
    ) {
        AttachmentContent content = attachmentService.getContent(tripId, attachmentId, currentUser);

        return ResponseEntity
                .ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + content.filename() + "\""
                )
                .contentType(MediaType.parseMediaType(content.contentType()))
                .body(new InputStreamResource(content.stream()));
    }

    @GetMapping()
    public ResponseEntity<List<AttachmentMetadataResponse>> getAttachments(
            @PathVariable Long tripId,
            @AuthenticationPrincipal User currentUser
    ) {
        return ResponseEntity.ok(
                attachmentService.getAttachments(tripId, currentUser)
        );
    }

    @Operation(
            summary = "Delete attachment content",
            description = """
                    Deletes content of the attachment.
                    
                    Requires authentication.
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "File successfully deleted",
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
    @DeleteMapping("/{attachmentId}")
    public void deleteAttachment(
            @PathVariable Long tripId,
            @PathVariable Long attachmentId,
            @AuthenticationPrincipal User currentUser
    ) {
        attachmentService.delete(tripId, attachmentId, currentUser);
    }
}
