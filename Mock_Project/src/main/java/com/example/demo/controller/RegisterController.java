package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Role;
import com.example.demo.entity.Trainer;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.SecurityService;
import com.example.demo.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserService userService;

	@Autowired
	SecurityService securityService;

	@RequestMapping(path = { "/register" }, method = RequestMethod.GET)
	public String getRegisterPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString) {
		model.addAttribute("errorString", errorString);
		System.out.println("register");
		return "register";
	}

	@RequestMapping(path = { "/register" }, method = RequestMethod.POST)
	public String postRegisterPage(Model model,
			HttpSession session,
			@RequestParam(name = "inputUserName", required = false) String inputUserName,
			@RequestParam(name = "inputPassword", required = false) String inputPassword,
			@RequestParam(name = "inputConfirmPassword", required = false) String inputConfirmPassword) {
		
		System.out.println("Registering...");
		boolean hasError = false;
		String errorString = null;

		if (inputUserName.isBlank() || inputPassword.isBlank() || inputConfirmPassword.isBlank()) {
			hasError = true;
			errorString = "Must enter all fields.";
		}
		
		if (!Objects.isNull(userRepository.findByUsername(inputUserName))) {
			hasError = true;
			errorString = "Username has been taken.";
		}

		if (!Objects.equals(inputPassword, inputConfirmPassword)) {
			hasError = true;
			errorString = "Password is not matched.";
		}

		if (hasError) {
			model.addAttribute("errorString", errorString);
			return "register";
		} else {
			Trainer user = new Trainer();
			user.setUsername(inputUserName);

			List<Role> listOfRole = new ArrayList<Role>();
			listOfRole.add(roleRepository.findByName("ROLE_USER"));
//			 listOfRole.add(roleRepository.findByName("ROLE_ADMIN"));

			userService.save(user, inputPassword, listOfRole);
			System.out.println("saved");
			securityService.autoLogin(user.getUsername(), inputPassword, session);
			return "redirect:/view_product";
		}
	}

}
