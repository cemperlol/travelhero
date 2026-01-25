package com.travel.hero.user.service;

import com.travel.hero.user.dto.CreateUserRequest;
import com.travel.hero.user.dto.UserResponse;
import com.travel.hero.user.exception.UserNotFoundException;
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
        return mapToUserResponse(findUser(uuid));
    }

    @Override
    public void delete(UUID uuid) {
        userRepository.delete(findUser(uuid));
    }

    private User findUser(UUID uuid) {
        return userRepository.findById(uuid)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                String.format("There is no user with uuid %s", uuid)
                        ));
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
