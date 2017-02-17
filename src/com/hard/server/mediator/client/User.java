package com.hard.server.mediator.client;

public interface User {
	public void setId(int id);
	public int getId();
	public void setName(String name);
	public String getName();
	public void sendMessage(String message);
	public void getMessage(String message);
}