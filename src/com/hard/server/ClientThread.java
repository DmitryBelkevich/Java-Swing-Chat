package com.hard.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.hard.server.mediator.client.User;
import com.hard.server.observable.Observable;

public class ClientThread extends Thread {
	private Socket socket;
	private User user;
	private BufferedReader isSocket;
	public PrintWriter osSocket;
	private Observable serverNotifier;
	
	public ClientThread(Socket socket, User user, Observable serverNotifier) {
		this.socket = socket;
		this.user = user;
		this.serverNotifier = serverNotifier;
	}
	
	@Override
	public void run() {
		try {
			isSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			osSocket = new PrintWriter(socket.getOutputStream(), true);
			
			user.getMessage("*** Type your name ***");
			
			String socketLine = null;
			
			String name = isSocket.readLine();
			user.setName(name);
			
			user.sendMessage("*** " + user.getName() + " has joined ***");
			serverNotifier.notifyObservers("*** " + user.getName() + " has joined ***");
			
			while ((socketLine = isSocket.readLine()) != null) {
				user.sendMessage(socketLine);
				serverNotifier.notifyObservers(socketLine);
			}
			
			isSocket.close();
			osSocket.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() throws IOException {
		//isSocket.close();
		osSocket.close();
		socket.close();
		System.out.println("closed");
	}
}