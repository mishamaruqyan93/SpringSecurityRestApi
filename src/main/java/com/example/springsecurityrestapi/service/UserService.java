package com.example.springsecurityrestapi.service;

import com.example.springsecurityrestapi.model.User;

import java.util.List;

public interface UserService {
    User register(User user);

    List<User> getAll();

    User findByUserName(String username);

    User findById(long id);

    void delete(long id);
}
