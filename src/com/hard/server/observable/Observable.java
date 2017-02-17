package com.hard.server.observable;

import com.hard.server.observer.Observer;

public interface Observable {
	public void addObserver(Observer observer);
	public void removeObserver(Observer observer);
	public void notifyObservers(String str);
}