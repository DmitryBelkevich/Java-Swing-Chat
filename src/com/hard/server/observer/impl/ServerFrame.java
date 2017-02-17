package com.hard.server.observer.impl;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.hard.server.Server;
import com.hard.server.observer.Observer;

public class ServerFrame implements Observer {
	public ServerFrame() throws IOException {
		createGui();
	}
	
	public void createGui() throws IOException {
		JFrame frame = new JFrame("Server");
		
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		frame.setLayout(new GridBagLayout());
		
		//JButton startButton = new JButton("Start");
		
		//frame.add(startButton);
		
		//startButton.addActionListener(new StartButtonActionListener());	// bug
		//frame.addWindowListener(new CloseWindowListener());				// bug
		
		frame.setVisible(true);
	}
	
	private class StartButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				Server.run(Server.DEFAULT_PORT);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private class CloseWindowListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			try {
				Server.stop();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void printMessage(String str) {
		System.out.println("to frame: " + str);
	}
	
	@Override
	public void update(String str) {
		printMessage(str);
	}
}