package com.springmvc.controller;

import com.springmvc.entity.User;
import com.springmvc.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class RootController {

    private final UserService userService;

    public RootController(
            UserService userService
//            AuthenticationService authenticationService
    ) {
        this.userService = userService;
//        this.authenticationService = authenticationService;
    }

    //  ----
    //  GET requests
    //  ----

    @RequestMapping(method = RequestMethod.GET)
    public String getIndex() {
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

    //  ----
    //  POST requests
    //  ----

    @RequestMapping(value = "sign-up", method = RequestMethod.POST)
    public String postSignIn(
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

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}

