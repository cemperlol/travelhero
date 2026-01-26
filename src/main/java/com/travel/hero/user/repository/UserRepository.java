package com.travel.hero.user.repository;

import com.travel.hero.user.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmail(@NotBlank @Email String email);

    boolean existsByUsername(
            @NotBlank @Size(min = 3, max = 32)
            @Pattern(regexp = "^[A-Za-z0-9_]{3,32}$")
            String username
    );
}
