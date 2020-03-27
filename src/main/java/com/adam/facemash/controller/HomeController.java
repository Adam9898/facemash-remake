package com.adam.facemash.controller;

import com.adam.facemash.domain.User;
import com.adam.facemash.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class HomeController {

    private UserService userService;

    @RequestMapping("/")
    public String mainRoute(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        String returnValue = "app";
        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        } else {
            userService.registerUser(user);
            // todo start new user session before redirecting
        }
        return returnValue;
    }

    @RequestMapping("/app")
    public String appRoute() { return "main"; }

    @RequestMapping("/top")
    public String topRoute() { return "top"; }
}
