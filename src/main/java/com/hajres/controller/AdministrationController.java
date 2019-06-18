package com.hajres.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import com.hajres.domain.dto.RegHelperUser;
import com.hajres.domain.entity.User;
import com.hajres.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administration")
public class AdministrationController {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(getClass().getName());

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/registration")
    public String showMyLoginPage(Model model) {

        model.addAttribute("regHelperUser", new RegHelperUser());

        return "administration/registration-form";
    }

    @PostMapping("/processRegistration")
    public String processRegistrationForm(
            @Valid @ModelAttribute("regHelperUser") RegHelperUser regHelperUser,
            BindingResult theBindingResult,
            Model theModel) {

        // form validation
        if (theBindingResult.hasErrors()) {
            return "administration/registration-form";
        }

        String userName = regHelperUser.getUsername();
        logger.info("Processing registration form for: " + userName);

        // check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null) {
            theModel.addAttribute("regHelperUser", new RegHelperUser());
            theModel.addAttribute("registrationError", "User name already exists.");

            logger.warning("User name already exists.");
            return "administration/registration-form";
        }
        // create user account
        userService.save(regHelperUser);

        logger.info("Successfully created user: " + userName);

        return "redirect:../worker/list";
    }
}
