package com.travel.hero.attachment.dto;

import java.io.InputStream;

public record AttachmentContent(
        InputStream stream,
        String filename,
        String contentType
) {}
