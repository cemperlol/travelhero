package com.travel.hero.attachment.service;

import com.travel.hero.attachment.dto.AttachmentMetadataResponse;
import com.travel.hero.attachment.dto.CreateAttachmentRequest;
import com.travel.hero.user.model.User;

public interface AttachmentService {

    AttachmentMetadataResponse create(CreateAttachmentRequest request, User currentUser);

    AttachmentMetadataResponse get(Long attachmentId, User currentUser);

    void delete(Long attachmentId, User currentUser);
}
