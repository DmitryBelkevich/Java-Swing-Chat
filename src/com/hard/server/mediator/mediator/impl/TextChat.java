package com.hard.server.mediator.mediator.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hard.server.Server;
import com.hard.server.mediator.client.User;
import com.hard.server.mediator.mediator.Chat;

public class TextChat implements Chat {
	private List<User> users = new ArrayList<>();
	
	public void addUser(User user) {
		users.add(user);
	}
	
	@Override
	public void sendMessage(String message, User user) {
		for (User u : users)
			u.getMessage("[" + user.getName() + "]:" + message);
		
		if (message.equals("/list"))
			printUserList();
		
		if (message.equals("/stop"))
			try {
				Server.stop();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@Override
	public void printUserList() {
		for (User u : users)
			System.out.println("[id=" + u.getId() + ", name=" + u.getName() + "]");
	}
}