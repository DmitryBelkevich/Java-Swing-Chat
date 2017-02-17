package com.hard.client.observable;

import com.hard.client.observer.Observer;

public interface Observable {
	public void addObserver(Observer observer);
	public void removeObserver(Observer observer);
	public void notifyObservers(String str);
}