package com.pico.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.pico.display.screens.Message;
import com.pico.prefs.PREFS;

public class MessageKeys implements KeyListener{
	public static int SHIFT = KeyEvent.VK_SHIFT;
	private Message message;
	
	public MessageKeys(Message _message){
		message = _message;
	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A){
				message.hurry();
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {

	} 

}
