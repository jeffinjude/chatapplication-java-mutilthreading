package com.jeffinjude.jfchat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The controller class that handles the index page.
 * @author JeffinJude
 *
 */
@Controller
public class IndexController {
	
	@Autowired
	ModelAndView mav;
	
	/**
	 * Handles the get request to index page.
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getIndex() {
		
		mav.setViewName("index");
		mav.addObject("message", "JfChat!!");
		
		return mav;
	}
	
}
