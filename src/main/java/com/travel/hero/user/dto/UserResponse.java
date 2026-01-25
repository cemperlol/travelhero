package com.travel.hero.user.dto;

import com.travel.hero.currency.enumeration.CurrencyCode;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record UserResponse(
        UUID uuid,
        String email,
        String username,
        LocalDateTime createdAt,
        LocalDateTime lastLoginAt,
        CurrencyCode currencyCode
) { }
