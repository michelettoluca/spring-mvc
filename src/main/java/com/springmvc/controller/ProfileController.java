package com.springmvc.controller;

import com.springmvc.entity.User;
import com.springmvc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    //  ----
    //  GET requests
    //  ----

    @RequestMapping(method = RequestMethod.GET)
    public String getProfileDetails(
            Model model
    ) {
        int tmpUserId = 1;

        User user = userService.findOneById(tmpUserId);

        model.addAttribute("user", user);

        return "profile";
    }
}

