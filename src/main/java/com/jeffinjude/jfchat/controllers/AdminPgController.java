package com.jeffinjude.jfchat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The controller class that handles the admin page.
 * @author JeffinJude
 *
 */
@Controller
public class AdminPgController {
	
	@Autowired
	ModelAndView mav;
	
	/**
	 * Handles the get request to admin page.
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView getIndex() {
		
		mav.setViewName("admin");
		mav.addObject("message", "Admin page!!");
		
		return mav;
	}
	
}
