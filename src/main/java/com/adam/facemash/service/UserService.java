package com.adam.facemash.service;

import com.adam.facemash.domain.User;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface UserService {
    void registerUser(User user);
    Map<?, ?> usernameChecker(String username);
    void startNewSession(User user, HttpSession httpSession);
}