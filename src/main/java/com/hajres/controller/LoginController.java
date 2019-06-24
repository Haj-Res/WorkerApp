package com.hajres.controller;

import com.hajres.domain.dto.RegHelperUser;
import com.hajres.domain.entity.User;
import com.hajres.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    @Qualifier(value = "userServiceImpl")
    UserService userService;
    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @GetMapping("/login")
    public String showLoginFOrm() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }

    @GetMapping("/registration")
    public String showMyLoginPage(Model model) {

        model.addAttribute("regHelperUser", new RegHelperUser());

        return "registration-form";
    }

    @PostMapping("/processRegistration")
    public String processRegistrationForm(
            @Valid @ModelAttribute("regHelperUser") RegHelperUser regHelperUser,
            BindingResult theBindingResult,
            Model theModel) {

        // form validation
        if (theBindingResult.hasErrors()) {
            return "registration-form";
        }

        String userName = regHelperUser.getUsername();
        logger.info("Processing registration form for: " + userName);

        // check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null) {
            theModel.addAttribute("regHelperUser", new RegHelperUser());
            theModel.addAttribute("registrationError", "User name already exists.");

            logger.warn("User name already exists.");
            return "registration-form";
        }
        // create user account
        userService.save(regHelperUser);

        logger.info("Successfully created user: " + userName);

        return "redirect:/worker/list";
    }

    @GetMapping("/error/404")
    public String show404(HttpServletRequest request, Model model) {
        model.addAttribute("method", request.getMethod());
        return "/error/404";
    }
}
