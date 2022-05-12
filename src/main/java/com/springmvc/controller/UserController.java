package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @RequestMapping
    public String setLuca(Model model) {
        model.addAttribute("name", "users");

        return "index";
    }

    @RequestMapping(value = "/{username}")
    public String setUsername(
            @PathVariable String username,
            @RequestParam(value = "action", required = false) String action,
            @RequestParam(value = "actions", required = false) String actions,
            Model model
    ) {
        model.addAttribute("name", username);
        model.addAttribute("action", action);
        model.addAttribute("actions", actions);

        return "index";
    }
}
