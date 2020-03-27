package com.adam.facemash.service;

import com.adam.facemash.domain.User;

import java.util.Map;

public interface UserService {
    void registerUser(User user);
    Map<?, ?> usernameChecker(String username);
}