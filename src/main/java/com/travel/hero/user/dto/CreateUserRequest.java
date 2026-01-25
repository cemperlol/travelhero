package com.travel.hero.user.dto;

public record CreateUserRequest(
        String email,
        String password,
        String username
) { }
