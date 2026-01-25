package com.travel.hero.user.service;

import com.travel.hero.currency.enumeration.CurrencyCode;
import com.travel.hero.user.dto.CreateUserRequest;
import com.travel.hero.user.dto.UserResponse;
import com.travel.hero.user.model.User;
import com.travel.hero.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse create(CreateUserRequest request) {
        User user = User.create(
                request.email(),
                request.password(),
                request.username()
        );

        user = userRepository.save(user);

        return mapToUserResponse(user);
    }

    @Override
    public UserResponse get(UUID uuid) {
        return ;
    }

    @Override
    public void delete(UUID uuid) {

    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .uuid(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .createdAt(user.getCreatedAt())
                .lastLoginAt(user.getLastLoginAt())
                .build();
    }
}
