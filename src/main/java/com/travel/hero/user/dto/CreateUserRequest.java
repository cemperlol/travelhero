package com.travel.hero.user.dto;

import com.travel.hero.currency.enumeration.CurrencyCode;

public record CreateUserRequest(
        String email,
        String password,
        String username,
        CurrencyCode currencyCode
) { }
