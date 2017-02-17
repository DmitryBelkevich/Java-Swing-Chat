package com.hard.server.mediator.mediator;

import com.hard.server.mediator.client.User;

public interface Chat {
	public void sendMessage(String message, User user);
	public void printUserList();
}