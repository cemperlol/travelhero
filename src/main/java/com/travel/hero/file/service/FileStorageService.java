package com.travel.hero.file.service;

import com.travel.hero.file.dto.StoredFile;

import java.io.InputStream;

public interface FileStorageService {

    StoredFile store(
            InputStream content,
            String contentType
    );

    InputStream load(String storageKey);

    void delete(String storageKey);
}
