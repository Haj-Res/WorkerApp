package com.hajres.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import com.hajres.domain.dto.UserDTO;
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
public class RegistrationController {

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
		
		model.addAttribute("userDTO", new UserDTO());
		
		return "registration-form";
	}

	@PostMapping("/processRegistration")
	public String processRegistrationForm(
				@Valid @ModelAttribute("userDTO") UserDTO userDTO,
				BindingResult theBindingResult, 
				Model theModel) {
		
		String username = userDTO.getUsername();
		logger.info("Processing registration form for: " + username);
		
		// form validation
		 if (theBindingResult.hasErrors()){
			 return "registration-form";
	        }

		// check the database if user already exists
        User existing = userService.findByUserName(username);
        if (existing != null){
        	theModel.addAttribute("userDTO", new UserDTO());
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "registration-form";
        }
     // create user account        						
        userService.save(userDTO);
        
        logger.info("Successfully created user: " + username);
        
        return "redirect:../worker/list";
	}
}
