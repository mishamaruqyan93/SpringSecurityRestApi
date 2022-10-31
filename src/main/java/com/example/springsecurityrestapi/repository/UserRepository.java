package com.example.springsecurityrestapi.repository;

import com.example.springsecurityrestapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String name);
}
