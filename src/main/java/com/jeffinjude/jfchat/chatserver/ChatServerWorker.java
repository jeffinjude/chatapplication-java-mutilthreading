package com.jeffinjude.jfchat.chatserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * The class that creates a new thread for each client connection to handle the chat.
 * @author JeffinJude
 *
 */
@Component
public class ChatServerWorker extends Thread{
	
	private String userName;
	private Socket clientSocket; //to access client socket of server worker
	private ChatServer server; // to access the chat server instance so that each server worker has access to server worker list to check user presence.
	final Logger log  = Logger.getLogger(ChatServerWorker.class.getName());
	private InputStream inputstream;
	private OutputStream outputstream;
	private Map<String,String> chatMessages = new HashMap<>();
	
	/**
	 * ChatServerWorker constructor
	 * @param clientSocket
	 * @param server
	 * @param userName
	 */
	public ChatServerWorker(Socket clientSocket, ChatServer server, String userName) {
		this.clientSocket = clientSocket;
		this.server = server;
		this.userName = userName;
	}
	
	/**
	 * ChatServerWorker default constructor
	 */
	public ChatServerWorker() {
		
	}
	
	/**
	 * Code for the thread to execute.
	 */
	@Override
	public void run() {
		try {
			handleClientSocket();
		} catch (IOException e) {
			if(e.getMessage().equalsIgnoreCase("Socket closed")) {
				log.info("Chat thread exits.Socket closed for user: " + getUserName());
			}
			else {
				log.error("IO exception in handleClientSocket. Exception: " + e.getMessage());
			}
		} catch (Exception e) {
			log.error("Exception in handleClientSocket. Exception: " + e.getMessage());
		}
	}
	
	/**
	 * Handles the client sockets, read from client socket's inputstream and write to client socket's outputstream
	 * i.e manages the chat stream.
	 * @throws IOException
	 */
	public void handleClientSocket() throws IOException {
		log.info("Chat thread started for user: " + getUserName());
		inputstream = clientSocket.getInputStream();
		outputstream = clientSocket.getOutputStream();
		
		BufferedReader reader =  new BufferedReader(new InputStreamReader(inputstream));
		String line;
		while((line = reader.readLine()) != null) {
			if("quit".equalsIgnoreCase(line)) {
				break;
			}
			String msg = line + "\n";
			//outputstream.write(msg.getBytes());
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			System.err.println("Reading input");
			this.getChatMessages().put(timeStamp, msg);
		}
		
		closeClientSocket();
	}
	
	/**
	 * Closes the client socket.
	 * @throws IOException
	 */
	public void closeClientSocket() throws IOException {
		clientSocket.close();
	}
	
	/**
	 * The overridden to string method of ServerWorker
	 * @return String
	 */
	@Override
	public String toString() {
		return "ServerWorker [clientSocket=" + clientSocket + ", server=" + server + ", log=" + log + "]";
	}
	
	/**
	 * Getter for username
	 * @return String
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Setter for username
	 * @return String
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public ChatServer getServer() {
		return server;
	}

	public void setServer(ChatServer server) {
		this.server = server;
	}

	public InputStream getInputstream() {
		return inputstream;
	}

	public void setInputstream(InputStream inputstream) {
		this.inputstream = inputstream;
	}

	public OutputStream getOutputstream() {
		return outputstream;
	}

	public void setOutputstream(OutputStream outputstream) {
		this.outputstream = outputstream;
	}

	public Map<String, String> getChatMessages() {
		return chatMessages;
	}

	public void setChatMessages(Map<String, String> chatMessages) {
		this.chatMessages = chatMessages;
	}
	
}
