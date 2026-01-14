package com.travel.hero.file.service;

import com.travel.hero.file.dto.StoredFile;
import com.travel.hero.file.exception.FileStorageException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class LocalFileStorageService implements FileStorageService{

    private final Path root = Paths.get("/data/files");

    @Override
    public StoredFile store(
            InputStream content,
            String originalFilename,
            String contentType) {
        String key = UUID.randomUUID().toString();

        long size;
        try {
             size = Files.copy(content, root.resolve(key));
        } catch (IOException e) {
            throw new FileStorageException(
                    "Failed to store file: " + originalFilename,
                    e.getCause()
            );
        }
        return new StoredFile(key, size);
    }

    @Override
    public InputStream load(String storageKey) {
        try {
            return Files.newInputStream(root.resolve(storageKey));
        } catch (IOException e) {
            throw new FileStorageException(
                    "Failed to load file at: " + storageKey,
                    e.getCause()
            );
        }
    }

    @Override
    public void delete(String storageKey) {
        try {
            Files.deleteIfExists(root.resolve(storageKey));
        } catch (IOException e) {
            throw new FileStorageException(
                    "Failed to delete file at: " + storageKey,
                    e.getCause()
            );
        }
    }
}
