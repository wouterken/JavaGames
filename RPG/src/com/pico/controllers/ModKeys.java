package com.pico.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.pico.prefs.PREFS;

public class ModKeys implements KeyListener{
	public static int SHIFT = KeyEvent.VK_SHIFT;
	
	public ModKeys(){
		KeyController.getInstance().registerListener(this);
	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == SHIFT){
			PREFS.STEPSIZE = PREFS.TURBOSTEPSIZE;
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == SHIFT){
			PREFS.STEPSIZE = PREFS.DEFAULTSTEPSIZE;
		}
	}

	public void keyTyped(KeyEvent e) {

	}
	
}
