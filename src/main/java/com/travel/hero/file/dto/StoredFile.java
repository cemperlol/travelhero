package com.travel.hero.file.dto;

public record StoredFile (
    String storageKey,
    Long size
) {}