package com.travel.hero.user.repository;

import com.travel.hero.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UUID, User> {

    
}
