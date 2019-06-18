package com.hajres.controller;

import com.hajres.domain.dto.EditUserDto;
import com.hajres.domain.dto.PasswordDto;
import com.hajres.domain.entity.User;
import com.hajres.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @GetMapping("/profile")
    public String showProfile(Model model, HttpServletRequest request) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        EditUserDto user = EditUserDto.map(sessionUser);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("user") EditUserDto userDto,
                           BindingResult bindingResult,
                           HttpServletRequest request,
                           Model model) {

        if (bindingResult.hasErrors()) {
            return "user/profile";
        }
        User user = (User) request.getSession().getAttribute("user");
        userDto.setUsername(user.getUsername());
        userService.update(userDto, user);
        request.getSession().setAttribute("user", user);
        model.addAttribute("message", "Profile updated.");

        return "redirect:../worker/list";
    }

    @GetMapping("/password")
    public String showPasswordForm(Model model) {
        PasswordDto passwordDto = new PasswordDto();
        model.addAttribute("password", passwordDto);
        return "user/password";
    }

    @PostMapping("/process-password")
    public String savePassword(@Valid @ModelAttribute("password") PasswordDto passwordDto,
                                BindingResult bindingResult,
                                HttpServletRequest request,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "user/password";
        }
        User user = (User) request.getSession().getAttribute("user");
        User result = userService.updatePassword(passwordDto, user);
        if (result == null) {
            model.addAttribute("errorMessage", "Wrong password.");
            return "user/password";
        }
        request.getSession().setAttribute("user", result);
        model.addAttribute("message", "Password updated");
        return "redirect:../user/profile";
    }
}
