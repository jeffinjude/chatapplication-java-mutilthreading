package com.jeffinjude.jfchat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.jeffinjude.jfchat.chatserver.ChatServer;

/**
 * The class that executes code after spring boot starts up.
 * @author JeffinJude
 *
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
	
	@Autowired
	ChatServer chatServer;
	
	/**
	* Starts up the chat server.
	*/
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		chatServer.start(); //Started chat server thread.
		return;
	}
}
