package com.adam.facemash.controller;

import com.adam.facemash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
//@RequestMapping("/api")
public class APIController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/uniqueUser/{user}")
    public Map<?, ?> usernameChecker(@PathVariable("user") String username) {
        return userService.usernameChecker(username);
    }

}
