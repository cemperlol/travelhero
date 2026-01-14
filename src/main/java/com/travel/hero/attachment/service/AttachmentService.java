package com.travel.hero.attachment.service;

import com.travel.hero.attachment.dto.AttachmentContent;
import com.travel.hero.user.model.User;

public interface AttachmentService {

    AttachmentContent get(Long attachmentId, User currentUser);
}
