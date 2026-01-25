package com.travel.hero.user.service;

import com.travel.hero.user.dto.CreateUserRequest;
import com.travel.hero.user.dto.UserResponse;

import java.util.UUID;

public interface UserService {

    UserResponse create(CreateUserRequest request);

    UserResponse get(UUID uuid);

    void delete(UUID uuid);
}
