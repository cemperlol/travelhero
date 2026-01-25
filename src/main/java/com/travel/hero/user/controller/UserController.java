package com.travel.hero.user.controller;

import com.travel.hero.user.dto.CreateUserRequest;
import com.travel.hero.user.dto.UserResponse;
import com.travel.hero.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponse> createUser(
            @RequestBody CreateUserRequest request
    ) {
        return ResponseEntity.ok(userService.create(request));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponse> getUser(
            @PathVariable UUID uuid
    ) {
        return ResponseEntity.ok(userService.get(uuid));
    }
}
