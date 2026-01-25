package com.travel.hero.user.service;

import com.travel.hero.user.dto.CreateUserRequest;
import com.travel.hero.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    @Override
    public UserResponse create(CreateUserRequest request) {
        return ;
    }

    @Override
    public UserResponse get(UUID uuid) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }
}
