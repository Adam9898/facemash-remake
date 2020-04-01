package com.adam.facemash.controller;

import com.adam.facemash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class APIController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * API endpoint that expects a GET request and checks if a given username is already in the database or not.
     * @param username The username that needs to be validated.
     * @return a boolean value based on the validation process
     */
    @GetMapping("/uniqueUser/{user}")
    public Map<?, ?> usernameChecker(@PathVariable("user") String username) {
        return userService.usernameChecker(username);
    }

}
