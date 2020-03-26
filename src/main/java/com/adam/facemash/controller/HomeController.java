package com.adam.facemash.controller;

import com.adam.facemash.domain.User;
import com.adam.facemash.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    private UserService userService;

    @RequestMapping("/")
    public String mainRoute(Model model) {
        model.addAttribute(new User(), "user");
        return "index";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute User user) {
        System.out.println("A new user has been registered: " + user.getUsername());
        // todo start new user session before redirecting
        return "app";
    }

    @RequestMapping("/app")
    public String appRoute() { return "main"; }

    @RequestMapping("/top")
    public String topRoute() { return "top"; }
}
