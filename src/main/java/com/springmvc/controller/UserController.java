package com.springmvc.controller;

import com.springmvc.entity.User;
import com.springmvc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping
    public String list(Model model) {
        List<User> users = userService.findAll();

        model.addAttribute("users", users);

        return "users";
    }

    @RequestMapping(value = "/{username}")
    public String details(
            @PathVariable String username,
            Model model
    ) {
        User user = userService.findOneByUsername(username);

        model.addAttribute("user", user);

        return "users/details";
    }
}
