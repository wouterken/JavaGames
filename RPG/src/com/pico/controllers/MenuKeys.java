package com.pico.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.pico.events.EventBus;
import com.pico.events.MenuDownEvent;
import com.pico.events.MenuUpEvent;

public class MenuKeys implements KeyListener {

	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_UP){
			EventBus.Fire(new MenuUpEvent());
		}else if(arg0.getKeyCode() == KeyEvent.VK_DOWN){
			EventBus.Fire(new MenuDownEvent());
		}
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
