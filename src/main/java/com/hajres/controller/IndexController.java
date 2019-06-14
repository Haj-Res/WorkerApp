package com.hajres.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model m) {
        m.addAttribute("page", 1);
        m.addAttribute("size", 10);
        return "redirect:/worker/list";
    }
}
