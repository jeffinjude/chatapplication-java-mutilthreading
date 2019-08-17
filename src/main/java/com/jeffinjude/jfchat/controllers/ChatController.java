package com.jeffinjude.jfchat.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jeffinjude.jfchat.chatserver.ChatServer;
import com.jeffinjude.jfchat.chatserver.ChatServerWorker;

/**
 * The controller class that handles the chat page.
 * @author JeffinJude
 *
 */
@Controller
public class ChatController {
	
	@Autowired
	ModelAndView mav;
	
	/**
	 * Handles the get request to chat page.
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public ModelAndView getChat() {
		ChatServerWorker chatServerWorker = ChatServer.getServerWorkerList().get("user1");
		
		mav.addObject("chatMessages", chatServerWorker.getChatMessages().toString());
		mav.setViewName("chat");
		return mav;
	}
	
	@RequestMapping(value = "/chat", method = RequestMethod.POST)
	public ModelAndView postChat(@RequestParam(value = "chatContent", required = false) String chatContent) throws IOException {
		ChatServerWorker chatServerWorker = ChatServer.getServerWorkerList().get("admin");
		chatServerWorker.getOutputstream().write(chatContent.getBytes());
		
		mav.setViewName("chat");
		return mav;
	}
}
