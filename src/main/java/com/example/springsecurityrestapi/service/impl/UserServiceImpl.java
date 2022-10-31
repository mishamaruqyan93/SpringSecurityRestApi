package com.example.springsecurityrestapi.service.impl;

import com.example.springsecurityrestapi.model.Role;
import com.example.springsecurityrestapi.model.Status;
import com.example.springsecurityrestapi.model.User;
import com.example.springsecurityrestapi.repository.RoleRepository;
import com.example.springsecurityrestapi.repository.UserRepository;
import com.example.springsecurityrestapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role role_user = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role_user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        user.setStatus(Status.ACTIVE);
        User registeredUser = userRepository.save(user);
        log.info("IN register user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> allUsers = userRepository.findAll();
        log.info("IN getAll - {} users found", allUsers.size());
        return allUsers;
    }

    @Override
    public User findByUserName(String username) {
        User user = userRepository.findByUsername(username);
        log.info("IN findByUserName - user: {} found by username: {}", user, username);
        return user;
    }

    @Override
    public User findById(long id) {
        User user = userRepository.findById(id).get();
        log.info("IN findById - user: {} found by id: {}", user, id);
        return user;
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
        log.info("In delete- user with id: {} successfully deleted");
    }
}
