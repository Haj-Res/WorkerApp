package com.hajres.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginFOrm() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

    @GetMapping("/error/404")
    public String show404(HttpServletRequest request, Model model) {
        model.addAttribute("method", request.getMethod());
        return "/error/404";
    }
}
