package com.travel.hero.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User creation information")
public record CreateUserRequest(

        @Schema(
                description = "User email",
                example = "mail@mail.com"
        )
        String email,

        @Schema(
                description = "User unique username",
                example = "SomePassword111"
        )
        String password,

        @Schema(
                description = "User unique username",
                example = "someusername111"
        )
        String username
) { }
