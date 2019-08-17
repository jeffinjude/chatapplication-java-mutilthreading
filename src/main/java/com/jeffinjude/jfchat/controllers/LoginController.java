package com.jeffinjude.jfchat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * The controller class that handles the login page.
 * @author JeffinJude
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	ModelAndView mav;
	
	/**
	 * Handles the get request to login page.
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLogin(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		
		if (error != null) {
			mav.addObject("error", "Invalid username and password!");
		}
		else {
			mav.addObject("error", "");
		}

		if (logout != null) {
			mav.addObject("msg", "You've been logged out successfully.");
		}
		else {
			mav.addObject("msg", "");
		}
		
		mav.setViewName("login");
		
		return mav;
	}

}
