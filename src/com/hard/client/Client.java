/* TODO-list */
// сделать выбор порта

package com.hard.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.hard.client.observable.Observable;
import com.hard.client.observable.impl.ClientNotifier;
import com.hard.client.observer.impl.ClientFrame;
import com.hard.client.observer.impl.Console;
import com.hard.client.observer.impl.Page;

public class Client {
	public static final String DEFAULT_HOST = "localhost";
	public static final int DEFAULT_PORT = 9999;
	
	private static Socket socket;
	private static BufferedReader isSocket;
	private static PrintWriter osSocket;
	
	public static void main(String[] args) throws IOException {
		// ----Observer/----
		Observable clientNotifier = new ClientNotifier();
		
		clientNotifier.addObserver(new ClientFrame());
		clientNotifier.addObserver(new Console());
		clientNotifier.addObserver(new Page());
		// ----/Observer----
		
		run(Client.DEFAULT_HOST, Client.DEFAULT_PORT);
		
		/* input/output socket */
		isSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		osSocket = new PrintWriter(socket.getOutputStream(), true);
		
		/* input console */
		new Thread() {
			@Override
			public void run() {
				BufferedReader isConsole = new BufferedReader(new InputStreamReader(System.in));
				String consoleLine = null;
				try {
					while ((consoleLine = isConsole.readLine()) != null) {
						Client.sendMessage(consoleLine);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		/* dialog */
		/* socket reading */
		String socketLine = null;
		
		while ((socketLine = isSocket.readLine()) != null) {
			clientNotifier.notifyObservers(socketLine);
		}
		
		close();
	}
	
	public static void sendMessage(String str) {
		osSocket.println(str);
		
		if (str.equalsIgnoreCase("/exit") || str.equalsIgnoreCase("/quit")) 
			close();
	}
	
	public static void run(String host, int port) {
		try {
			socket = new Socket(host, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void close() {
		try {
			//isSocket.close();	//блокирует закрытие окна
			osSocket.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}