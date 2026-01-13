package com.travel.hero.file.service;

import com.travel.hero.file.dto.StoredFile;

import java.io.BufferedInputStream;

public interface FileStorageService {

    StoredFile store(
            BufferedInputStream content,
            String originalFilename,
            String contentType
    );

    BufferedInputStream load(String storageKey);

    void delete(String storageKey);
}
