package com.travel.hero.user.service;

import java.util.UUID;

public interface UserService {

    UserResponse getUser(UUID id);
}
