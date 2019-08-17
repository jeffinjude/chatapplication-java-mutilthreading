package com.jeffinjude.jfchat.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeffinjude.jfchat.chatserver.ChatServer;

/**
 * The controller class that handles the logout function.
 * @author JeffinJude
 *
 */
@Controller
public class LogoutController {
	
	final Logger log  = Logger.getLogger(LogoutController.class.getName());
	
	/**
	 * Handles the get request to logout and redirect to login page.
	 * @return String
	 * @throws IOException 
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) throws IOException {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){
	    	if(ChatServer.getServerWorkerList().get(auth.getName()) != null) {
	    		ChatServer.getServerWorkerList().get(auth.getName()).closeClientSocket(); //close client socket
	    	}
	    	ChatServer.getServerWorkerList().remove(auth.getName()); //Remove logged out user from server worker list.
			log.info("Removed " + auth.getName() + " from server worker list");
			log.info("Server Worker List:" + ChatServer.getServerWorkerList().toString());
	    	new SecurityContextLogoutHandler().logout(request, response, auth); //Perform log out.
	    }
	    return "redirect:/login?logout";//Redirect to login screen again.
	}
	 
}
