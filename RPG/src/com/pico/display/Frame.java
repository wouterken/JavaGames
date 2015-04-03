package com.pico.display;


import javax.swing.JFrame;

import com.pico.prefs.PREFS;


public class Frame extends JFrame{
	
	public Frame(){
		super(PREFS.GAMETITLE);	
	}
}
