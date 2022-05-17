package com.springmvc.controller;

import com.springmvc.entity.User;
import com.springmvc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAllUsers(
            Model model
    ) {
        List<User> users = userService.findAll();

        model.addAttribute("users", users);

        return "users";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String getSaveUser(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String origin,
            @RequestParam(required = false) String action,
            Model model
    ) {
        User user = new User();
        if (id != null) user = userService.findOneById(id);
        model.addAttribute("user", user);

        if (origin != null && action != null) return origin + "/" + action;

        return "sign-up";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String postSaveUser(
            @RequestParam(required = false) String origin,
            @ModelAttribute("user") User user
    ) {
        String redirectTo = "redirect:/";

        userService.save(user);

        if (origin != null) return redirectTo + origin + "?userId=" + user.getId();

        return redirectTo + "index";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String postSaveUser(
            @ModelAttribute("user") User user
    ) {
        userService.delete(user.getId());

        return "redirect:/admin";
    }
}
