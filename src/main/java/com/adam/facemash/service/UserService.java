package com.adam.facemash.service;

import com.adam.facemash.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {
    void registerUser(User user);
    Map<?, ?> usernameChecker(String username);
    void startNewSession(String username, String password, HttpServletRequest request);
}