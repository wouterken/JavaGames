package com.pico.display.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import com.pico.controllers.MenuKeys;
import com.pico.display.Controller;
import com.pico.events.EventBus;
import com.pico.events.GameEvent;
import com.pico.events.GameEventListener;
import com.pico.events.MenuDownEvent;
import com.pico.events.MenuUpEvent;
import com.pico.system.Game;

public class GameMenu extends GameEventListener implements Screen {

	private ArrayList<KeyListener> keyListeners = new ArrayList<KeyListener>();

	public GameMenu(){
		keyListeners.add(new MenuKeys());
		EventBus.registerListener(new MenuUpEvent(), this);
		EventBus.registerListener(new MenuDownEvent(), this);
	}
	public void draw(Graphics gffx) {
		// TODO Auto-generated method stub
		
	}

	public void draw() {
		// TODO Auto-generated method stub
		
	}

	public void graphics(Graphics2D gffx) {
		// TODO Auto-generated method stub
		
	}

	public void overlayGraphics(Graphics2D overlayGffx) {
		// TODO Auto-generated method stub
		
	}
	

	public void draw(Graphics gffx, int width, int height) {
		gffx.setColor(Color.black);
		gffx.fillRect(100, 100, width-300, height-300);
		gffx.setColor(Color.white);
		gffx.drawString("This is a test!", 300, 300);
		
		
	}

	public ArrayList<KeyListener> getKeyListeners() {
		
		return keyListeners;
	}
	public void fire(GameEvent event) {
		String eventType = event.getClass().toString();
		System.out.println(eventType);
		System.out.println(MenuDownEvent.class.toString());
		if(eventType.equals(MenuDownEvent.class.toString())){
			Controller.getInstance().setScreen(Game.gameDisplay);
		}
		else if(eventType.equals(MenuUpEvent.class.toString())){
			System.out.println("up!");
		}
	}

	
}
