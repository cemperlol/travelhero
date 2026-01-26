package com.travel.hero.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "User creation information")
public record CreateUserRequest(

        @Schema(
                description = "User email",
                example = "mail@mail.com"
        )
        @NotBlank @Email
        String email,

        @Schema(
                description = "User unique username",
                example = "SomePassword111"
        )
        @NotBlank @Size(min = 8, max = 32)
        @Pattern(regexp = "^[A-Za-z0-9!#$]{8,32}$")
        String password,

        @Schema(
                description = "User unique username",
                example = "someusername111"
        )
        @NotBlank @Size(min = 3, max = 32)
        @Pattern(regexp = "^[A-Za-z0-9_]{3,32}$")
        String username
) { }
