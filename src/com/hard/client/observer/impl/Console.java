package com.hard.client.observer.impl;

import com.hard.client.observer.Observer;

public class Console implements Observer {
	@Override
	public void update(String str) {
		System.out.println("print to console: " + str);
	}
}