package com.hard.server.observer.impl;

import com.hard.server.observer.Observer;

public class Console implements Observer {
	public void print(String str) {
		System.out.println("to console: " + str);
	}
	
	@Override
	public void update(String str) {
		print(str);
	}
}