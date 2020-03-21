package com.adam.facemash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class HomeController {

    @RequestMapping("/")
    public String mainRoute() {
        return "index";
    }

    @RequestMapping("/app")
    public String appRoute() { return "main"; }

    @RequestMapping("/top")
    public String topRoute() { return "top"; }
}
