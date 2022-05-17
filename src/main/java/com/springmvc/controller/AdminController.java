package com.springmvc.controller;

import com.springmvc.entity.User;
import com.springmvc.service.UserService;
import com.springmvc.type.UserRole;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    //  ----
    //  GET requests
    //  ----

    @RequestMapping(method = RequestMethod.GET)
    public String getDashboard(
            @RequestParam(required = false) Integer userId,
            Model model
    ) {
        List<User> customers = userService.findManyByRole(UserRole.CUSTOMER);

        model.addAttribute("customers", customers);

        if (userId != null) {
            User user = userService.findOneById(userId);
            model.addAttribute("user", user);
        }

        return "admin";
    }
}

