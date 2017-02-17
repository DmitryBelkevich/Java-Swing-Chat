package com.hard.server.observer.impl;

import com.hard.server.observer.Observer;

public class Database implements Observer {
	public void add(String str) {
		System.out.println("to database: " + str);
	}
	
	@Override
	public void update(String str) {
		add(str);
	}
}