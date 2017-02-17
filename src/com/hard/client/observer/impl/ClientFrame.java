package com.hard.client.observer.impl;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.hard.client.Client;
import com.hard.client.observer.Observer;

public class ClientFrame implements Observer {
	private JTextArea displayTextArea = new JTextArea(20, 50);
	private JTextField inputTextField = new JTextField();
	private JButton sendButton = new JButton("send");
	
	public ClientFrame() {
		createGui();
	}
	
	public void createGui() {
		JFrame frame = new JFrame("Client");
		
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		frame.setLayout(new GridBagLayout());
		
		displayTextArea.setEditable(false);
		
		JScrollPane scrollPanel = new JScrollPane(displayTextArea);
		
		frame.add(scrollPanel, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		frame.add(inputTextField, new GridBagConstraints(0, 1, 1, 1, 2.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 5, 5), 0, 0));
		frame.add(sendButton, new GridBagConstraints(1, 1, 1, 1, 0.1, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 5), 0, 0));
		
		SendButtonActionListener sendButtonActionListener = new SendButtonActionListener();
		inputTextField.addActionListener(sendButtonActionListener);
		sendButton.addActionListener(sendButtonActionListener);
		frame.addWindowListener(new CloseWindowListener());
		
		frame.setVisible(true);
		frame.pack();
		inputTextField.requestFocus();
	}
	
	private class SendButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String str = inputTextField.getText();
			if (!(str.equals("")))
				Client.sendMessage(str);
			
			inputTextField.requestFocus();
			inputTextField.setText(null);
		}
	}
	
	private class CloseWindowListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			Client.close();
		}
	}
	
	public void printMessage(String str) {
		displayTextArea.append(str + "\n");
	}
	
	@Override
	public void update(String str) {
		printMessage(str);
	}
}