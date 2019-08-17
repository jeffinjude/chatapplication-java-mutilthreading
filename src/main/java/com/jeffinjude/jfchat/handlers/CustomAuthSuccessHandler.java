package com.jeffinjude.jfchat.handlers;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.jeffinjude.jfchat.chatserver.ChatServer;

/**
 * The Class that executes business logic after user authentication is success.
 * @author JeffinJude
 *
 */
@Component(value="authSuccessHandler")
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	RedirectStrategy redirectStrategy;
	
	final Logger log  = Logger.getLogger(CustomAuthSuccessHandler.class.getName());
	
	/**
	 * Function that sets the logged in user to the static user var of chat server instance
	 * and creates a socket connection to chat server for logged in user.
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth)
			throws IOException, ServletException {
		ChatServer.loggedUserName = auth.getName(); //Set the logged in username.
		log.info("Authentication Success for user: " + ChatServer.loggedUserName);
		InetAddress ipAddr = InetAddress.getLocalHost();
		int port = 8818; //TODO: Move server port to property file.
		isLoggedIn(auth.getName());
		Socket socket = new Socket(ipAddr, port); //For each logged in user create a socket connection to chat server.
		log.info("Created socket for user: " + ChatServer.loggedUserName);
		redirectStrategy.sendRedirect(req, res, "/"); // redirect to index page.
	}
	
	/**
	 * Check whether the user is currently logged in, if so remove the old chat server worker
	 * and close the previous chat server socket assigned to that user
	 * @throws IOException 
	 */
	public void isLoggedIn(String userName) throws IOException {
		if(ChatServer.getServerWorkerList().containsKey(userName)) {
			log.info("User " + userName + " is already logged in. Closing previous client socket.");
			if(ChatServer.getServerWorkerList().get(userName) != null) {
				ChatServer.getServerWorkerList().get(userName).closeClientSocket(); //close client socket.
			}
	    	ChatServer.getServerWorkerList().remove(userName); //remove the user from server worker list.
		}
	}
}
