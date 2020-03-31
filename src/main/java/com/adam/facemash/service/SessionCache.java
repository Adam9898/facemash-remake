package com.adam.facemash.service;

import com.adam.facemash.dao.User;
import com.adam.facemash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * The session cache class uses session scope, and can be injected into other classes to get the current User object back.
 * It can be useful in certain scenarios where the class we inject the cache into has a method that gets invoked
 * multiple times, and that method needs the currently logged in User object, and the code could only retrieve it by
 * querying a datasource again and again.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionCache {

    UserRepository userRepository;
    private Authentication auth;
    private User currentlyLoggedInUser;


    @Autowired
    public SessionCache(UserRepository userRepository) {
        this.userRepository = userRepository;
        auth = SecurityContextHolder.getContext().getAuthentication();
        currentlyLoggedInUser = userRepository.findByUsername(auth.getName());
    }

    public User getCurrentlyLoggedInUser() {
        return currentlyLoggedInUser;
    }
}
