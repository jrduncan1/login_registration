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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	// dashboard page for logged in or registered users
	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		if(session.getAttribute("uuid") == null) {
			return "redirect:/";
		}
		model.addAttribute("loggedUser", userService.retrieveOneUser((Long) session.getAttribute("uuid")));
		return "dashboard.jsp";
	}
	
	// returns user to login page after logging out and clearing session
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
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
	
	// processes login of user
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session, RedirectAttributes redirect) {
		User user = userService.login(newLogin, result);
		if(result.hasErrors()) {
			redirect.addFlashAttribute("newLogin", newLogin);
			return "redirect:/";
		}
		session.setAttribute("uuid", user.getId());
		return "redirect:/dashboard";
	}
	
}
