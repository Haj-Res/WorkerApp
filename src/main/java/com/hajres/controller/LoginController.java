package com.hajres.controller;

import com.hajres.domain.dto.RegHelperUser;
import com.hajres.domain.entity.User;
import com.hajres.domain.entity.news.Country;
import com.hajres.service.NewsParameterService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    @Qualifier(value = "userServiceImpl")
    private UserService userService;
    
    @Autowired
    private NewsParameterService newsParameterService;

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

        Map<String, String> categories = newsParameterService.getNewsCategoryMap();
        Map<String, String> countries = mapCountriesFromList(newsParameterService.getCountries());
        model.addAttribute("regHelperUser", new RegHelperUser());
        model.addAttribute("countries", countries);
        model.addAttribute("categories", categories);
        return "registration-form";
    }

    @PostMapping("/processRegistration")
    public String processRegistrationForm(
            @Valid @ModelAttribute("regHelperUser") RegHelperUser regHelperUser,
            BindingResult theBindingResult,
            Model model) {

        Map<String, String> categories = newsParameterService.getNewsCategoryMap();
        Map<String, String> countries = mapCountriesFromList(newsParameterService.getCountries());
        model.addAttribute("countries", countries);
        model.addAttribute("categories", categories);

        // form validation
        if (theBindingResult.hasErrors()) {
            return "registration-form";
        }

        String userName = regHelperUser.getUsername();
        logger.info("Processing registration form for: " + userName);

        // check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null) {
            model.addAttribute("regHelperUser", new RegHelperUser());
            model.addAttribute("registrationError", "User name already exists.");

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

    private Map<String, String> mapCountriesFromList(List<Country> countryList) {
        Map<String, String> countries = new HashMap<>();
        countryList.forEach(c -> {
            String name = c.getInternationalName() + " (" + c.getLocalName() + ")";
            countries.put(c.getCountryId(), name);
        });
        return countries;
    }
}
