package com.jduncan.loginregistration.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jduncan.loginregistration.models.LoginUser;
import com.jduncan.loginregistration.models.User;
import com.jduncan.loginregistration.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// **** DISPLAY ROUTES ****
	// login & reg page
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		if(session.getAttribute("uuid") != null) {
			return "redirect:/dashboard";
		}
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
	
	// **** ACTION ROUTES ****
	// processes registration of user
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		userService.register(newUser, result);
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		session.setAttribute("uuid", newUser.getId());
		return "redirect:/dashboard";
	}
}
