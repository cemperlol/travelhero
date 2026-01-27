package com.travel.hero.file.service;

import java.io.InputStream;

public interface FileStorageService {

    String store(
            InputStream content,
            String contentType
    );

    InputStream load(String storageKey);

    void delete(String storageKey);
}
