package com.pico.display.screens;

import java.awt.Graphics2D;

import com.pico.prefs.PREFS;

public abstract class UIComponent implements Screen {
	
	public void clear(Graphics2D gffx){
		gffx.clearRect(0, 0, PREFS.GAMEWIDTH, PREFS.GAMEHEIGHT);
	}


}
