package com.adam.facemash.controller;

import com.adam.facemash.domain.User;
import com.adam.facemash.service.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;


@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);
    private UserService userService;

    @RequestMapping("/")
    public String mainRoute(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
                               HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            logger.error(bindingResult.getFieldErrors().toString());
        } else {
            userService.registerUser(user);
            userService.startNewSession(user, httpSession);
        }
        return "redirect:/app";
    }

    @Secured("ROLE_REGULAR")
    @RequestMapping("/app")
    public String appRoute() {
        return "main";
    }

    @Secured("ROLE_REGULAR")
    @RequestMapping("/top")
    public String topRoute() { return "top"; }
}
