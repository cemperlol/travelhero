package com.travel.hero.user.dto;

import com.travel.hero.currency.enumeration.CurrencyCode;

import java.time.LocalDateTime;

public record UserResponse(
        String email,
        String password,
        String username,
        LocalDateTime createdAt,
        LocalDateTime lastLoginAt,
        CurrencyCode currencyCode
) { }
