package com.travel.hero.user.dto;

import com.travel.hero.currency.enumeration.CurrencyCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record UserResponse(

        @Schema(
                description = "User UUID",
                example = "f47ac10b-58cc-4372-a567-0e02b2c3d479"
        )
        UUID uuid,

        @Schema(
                description = "User email",
                example = "mail@mail.com"
        )
        String email,

        @Schema(
                description = "User unique username",
                example = "someusername111"
        )
        String username,

        @Schema(
                description = "User creation time",
                example = "2026-01-26T18:04:27.792280600"
        )
        LocalDateTime createdAt,

        @Schema(
                description = "User last login time",
                example = "2026-01-26T18:04:27.792280600"
        )
        LocalDateTime lastLoginAt,

        @Schema(
                description = "User base currency",
                example = "USD"
        )
        CurrencyCode currencyCode
) { }
