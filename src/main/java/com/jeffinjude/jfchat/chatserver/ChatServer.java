package com.jeffinjude.jfchat.chatserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The chat server thread that creates a server worker thread for each client
 * that is connected to the chat server.<br>
 * ChatServer thread -- > ServerWorker1 thread maintains chat stream for user1<br>
 * 					 -- > ServerWorker2 thread maintains chat stream for user2<br>
 * 					 -- > .........<br>
 * @author JeffinJude
 *
 */
@Component
public class ChatServer extends Thread{
	
	@Autowired
	ServerSocket serverSocket;
	
	final Logger log  = Logger.getLogger(ChatServer.class.getName());
	public static String loggedUserName = ""; //Note that this variable will overwrite with username of the last logged in user.
	
	private static Map<String,ChatServerWorker> serverWorkerList= new HashMap<>(); //Map that holds the user is session and his corresponding server worker instance.
	
	@Override
	public void run() {
		
		try {
			log.info("Started the chat server");
			while(true) { // keep listening for clients in an infinite loop
				log.info("Waiting for client!!");
				Socket clientSocket = serverSocket.accept(); // accept all clients trying to connect
				log.info("Accepted Connection from " + clientSocket);
				//chatServerWorker obj needs dynamically created clientSocket obj, hence cannot be autowired
				ChatServerWorker chatServerWorker = new ChatServerWorker(clientSocket, this, loggedUserName);
				serverWorkerList.put(loggedUserName,chatServerWorker); //TODO: Change this to REDIS in memory db
				log.info("Server Worker List:" + serverWorkerList.toString());
				chatServerWorker.start(); // Spawn new thread for each client connection
			}
		} catch (IOException e) {
			log.error("IO exception while starting chat server. Exception: " + e.getMessage());
		} catch (Exception e) {
			log.error("Exception while starting chat server. Exception: " + e.getMessage());
		}
	}
	
	/**
	 * Getter for server worker list
	 * @return Map<String, ChatServerWorker>
	 */
	public static Map<String, ChatServerWorker> getServerWorkerList() {
		return serverWorkerList;
	}
	
}
