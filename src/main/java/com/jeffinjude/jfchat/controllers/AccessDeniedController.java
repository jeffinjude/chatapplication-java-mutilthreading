package com.jeffinjude.jfchat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The controller class that handles the access denied page.
 * @author JeffinJude
 *
 */
@Controller
public class AccessDeniedController {
	
	@Autowired
	ModelAndView mav;
	
	/**
	 * Handles the get request to access denied page.
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/access_denied", method = RequestMethod.GET)
	public ModelAndView getAccessDenied() {
		
		mav.setViewName("access_denied");
		mav.addObject("message", "Access denied. You don't have permission to view this page!");
		
		return mav;
	}
	
}
