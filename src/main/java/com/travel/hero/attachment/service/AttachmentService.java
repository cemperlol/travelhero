package com.travel.hero.attachment.service;

import com.travel.hero.attachment.dto.AttachmentContent;
import com.travel.hero.attachment.dto.AttachmentMetadataResponse;
import com.travel.hero.attachment.dto.CreateAttachmentCommand;
import com.travel.hero.user.model.User;

import java.util.List;

public interface AttachmentService {

    AttachmentMetadataResponse create(CreateAttachmentCommand command, User currentUser);

    AttachmentContent getContent(Long tripId, Long attachmentId, User currentUser);

    List<AttachmentMetadataResponse> getAttachments(Long tripId, User currentUser);

    void delete(Long tripId, Long attachmentId, User currentUser);
}
