/* TaskList: */

// создать Database сообщений
// благодаря этому, если другой пользователь войдёт познее, он увидит предыдущие сообщения
// появится возможность видеть пользователей в режиме online/offline

// сделать проверку пользователей онлайн и отоброжение списка у клентов

// прописать корректные disconnects clients, возможно придётся заменить BufferedReader

// соответственно корректные close

// сделать удаление пользователей и их потоков
// закрытие сервера, если пользователей нет (по желанию)

// выбор порта и хоста в cmd или в textField в GUI у сервера и клиента

// добавить роли: admin

// добавить isEnable пользователей и применить данный параметр
// следовательно недоступные пользователи не получают сообщений

// удаление пользователя из массива потоков, если пользователь вышел

package com.hard.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.hard.server.mediator.client.User;
import com.hard.server.mediator.client.impl.SimpleUser;
import com.hard.server.mediator.mediator.impl.TextChat;
import com.hard.server.observable.Observable;
import com.hard.server.observable.impl.ServerNotifier;
import com.hard.server.observer.Observer;
import com.hard.server.observer.impl.Console;
import com.hard.server.observer.impl.Database;
import com.hard.server.observer.impl.ServerFrame;

public class Server implements Observable {
	public static final int DEFAULT_PORT = 9999;
	private static final int MAX_CLIENTS_COUNT = 3;
	
	private static ServerSocket serverSocket;
	private static TextChat textChat;
	private static ClientThread[] clientThreads = new ClientThread[Server.MAX_CLIENTS_COUNT];
	private static Observable serverNotifier;
	
	// ----mediadtor/----
	static {
		textChat = new TextChat();
	}
	// ----/mediadtor----
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// ----Observer/----
		serverNotifier = new ServerNotifier();
		
		serverNotifier.addObserver(new ServerFrame());
		serverNotifier.addObserver(new Database());
		serverNotifier.addObserver(new Console());
		// ----/Observer----
		
		run(Server.DEFAULT_PORT);
	}
	
	public static void run(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		
		int i = 0;
		while (true) {
			Socket socket = serverSocket.accept();
			
			// ----mediadtor/----
			User user = new SimpleUser(textChat, i);
			textChat.addUser(user);
			// ----/mediadtor----
			
			(clientThreads[i++] = new ClientThread(socket, user, serverNotifier)).start();
		}
	}
	
	public static void stop() throws IOException {
		for (ClientThread clientThread : clientThreads)
			clientThread.close();
		
		serverSocket.close();
	}
	
	public static ClientThread getClientThread(int i) {
		return clientThreads[i];
	}
	
	@Override
	public void addObserver(Observer observer) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void removeObserver(Observer observer) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void notifyObservers(String str) {
		// TODO Auto-generated method stub
		
	}
}