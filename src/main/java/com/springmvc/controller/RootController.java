package com.springmvc.controller;

import com.springmvc.entity.User;
import com.springmvc.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/")
public class RootController {

    private final UserService userService;

    public RootController(UserService userService) {
        this.userService = userService;
    }

    //  ----
    //  GET requests
    //  ----

    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(Model model) {

        return "index";
    }

    @RequestMapping(value = "sign-in", method = RequestMethod.GET)
    public String getSignIn(
            Model model
    ) {
        User user = new User();

        model.addAttribute("user", user);

        return "sign-in";
    }

    @RequestMapping(value = "sign-up", method = RequestMethod.GET)
    public String getSignUp(
            Model model
    ) {
        User user = new User();

        model.addAttribute("user", user);

        return "sign-up";
    }

    @RequestMapping(value = "/sign-out", method = RequestMethod.GET)
    public String getSignOut(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) new SecurityContextLogoutHandler().logout(request, response, auth);

        return "redirect:/";
    }

    //  ----
    //  POST requests
    //  ----

    @RequestMapping(value = "sign-up", method = RequestMethod.POST)
    public String postSignUp(
            @ModelAttribute("user") User user
    ) {
        switch (user.getRole()) {
            case ADMIN:
                return "redirect:/admin";

            case CUSTOMER:
                return "redirect:/profile";

            default:
                return "redirect:/";
        }
    }
}

