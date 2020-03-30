package com.adam.facemash.controller;

import com.adam.facemash.domain.User;
import com.adam.facemash.service.PersonService;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;


@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);
    private UserService userService;
    private PersonService personService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/")
    public String mainRoute(Model model, Principal principal) {
        String returnValue = "index";
        if (principal != null) {
            returnValue = "redirect:/app";
        } else {
            model.addAttribute("user", new User());
        }

        return returnValue;
    }

    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
                               HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            logger.error(bindingResult.getFieldErrors().toString());
        } else {
            String username = user.getUsername();
            String password = user.getPassword();
            userService.registerUser(user);
            userService.startNewSession(username, password, request);
        }
        return "redirect:/app";
    }

    @Secured("ROLE_REGULAR")
    @RequestMapping("/app")
    public String appRoute(Model model, Principal principal) {
        model.addAttribute("girls", personService.generatePeople(principal.getName()));
        return "main";
    }

    @Secured("ROLE_REGULAR")
    @RequestMapping("/top")
    public String topRoute() { return "top"; }
}
