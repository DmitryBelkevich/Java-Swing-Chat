package com.hard.server.mediator.client.impl;

import com.hard.server.Server;
import com.hard.server.mediator.client.User;
import com.hard.server.mediator.mediator.Chat;

public class SimpleUser implements User {
	private Chat chat;
	private int id;
	private String name;
	
	public SimpleUser(Chat chat, int id) {
		this.chat = chat;
		this.id = id;
	}
	
	@Override
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void sendMessage(String message) {
		chat.sendMessage(message, this);
	}
	
	@Override
	public void getMessage(String message) {
		Server.getClientThread(id).osSocket.println(message);
	}
}