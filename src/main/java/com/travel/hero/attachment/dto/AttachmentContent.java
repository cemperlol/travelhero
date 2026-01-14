package com.travel.hero.attachment.dto;

import java.io.InputStream;

public record AttachmentContent(
        InputStream content,
        String filename,
        String contentType
) {}
