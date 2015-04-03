package com.pico.display;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import com.pico.display.screens.Screen;
import com.pico.prefs.PREFS;



public class GameCanvas extends JComponent{


	public static Screen screen;
	private static GameCanvas instance;
	private long timeNow;
	private long timeSinceLast;
	private long timeLast;
	private int totalFPS = 0;
	private int numberOfRefreshes = 0;
	private boolean gffxInitialized;
	
	public GameCanvas(){
		super();
		setPreferredSize(new Dimension(PREFS.GAMEWIDTH,PREFS.GAMEHEIGHT));
	}

	public static GameCanvas getInstance(){
		if(instance == null){
			instance = new GameCanvas();
		}
		return instance;
	}
	@Override
	protected void paintComponent(Graphics _gffx) {
		if(!gffxInitialized){
			PREFS.initGffx((Graphics2D) _gffx);
			gffxInitialized = true;
		}
		timeNow = System.currentTimeMillis();
		timeSinceLast = timeNow - timeLast;
		PREFS.CURFPS = (int) (1000/timeSinceLast);
		totalFPS += PREFS.CURFPS;
		numberOfRefreshes++;
		PREFS.AVERAGEFPS = totalFPS/numberOfRefreshes ;
		timeLast = timeNow;
		if(screen == null) return;
		screen.draw(_gffx,getWidth(),getHeight());
	}

	public void setScreen(Screen _screen) {
		screen = _screen;
	}
	@Override
	public void setBounds(int x, int y, int width, int height) {
		PREFS.CANVASWIDTH = width;
		PREFS.CANVASHEIGHT = height;
		super.setBounds(x, y, width, height);
	}
	@Override
	public void setSize(Dimension d) {
		PREFS.CANVASWIDTH = d.width;
		PREFS.CANVASHEIGHT = d.height;
		super.setSize(d);
	}


}