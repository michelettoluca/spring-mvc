package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping
    public String setLuca(Model model) {
        model.addAttribute("name", "Luca");

        return "index";
    }

    @RequestMapping(value = "**")
    public String setAltro(Model model) {
        model.addAttribute("name", "**");

        return "index";
    }
}

