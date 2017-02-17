package com.hard.client.observer.impl;

import com.hard.client.observer.Observer;

public class Page implements Observer {
	public void print(String str) {
		// TODO
		System.out.println("print to jsp: " + str);
	}
	
	@Override
	public void update(String str) {
		print(str);
	}
}