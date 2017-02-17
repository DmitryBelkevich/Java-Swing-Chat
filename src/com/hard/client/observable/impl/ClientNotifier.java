package com.hard.client.observable.impl;

import java.util.ArrayList;
import java.util.List;

import com.hard.client.observable.Observable;
import com.hard.client.observer.Observer;

public class ClientNotifier implements Observable {
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