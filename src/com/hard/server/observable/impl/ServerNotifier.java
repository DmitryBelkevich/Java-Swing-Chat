package com.hard.server.observable.impl;

import java.util.ArrayList;
import java.util.List;

import com.hard.server.observable.Observable;
import com.hard.server.observer.Observer;

public class ServerNotifier implements Observable {
	private List<Observer> observers = new ArrayList<>();
	
	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	
	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	
	@Override
	public void notifyObservers(String str) {
		for (Observer observer : observers)
			observer.update(str);
	}
}