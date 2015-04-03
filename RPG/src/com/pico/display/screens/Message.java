package com.pico.display.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.pico.controllers.KeyController;
import com.pico.controllers.MessageKeys;
import com.pico.display.Controller;
import com.pico.display.GameCanvas;
import com.pico.display.screens.GameScreen;
import com.pico.prefs.PREFS;
import com.pico.system.Game;

public class Message implements Screen{

	private static Message instance;
	private GameScreen gameScreen;
	private String currentMessage = "";
	
	int bottom = PREFS.CANVASHEIGHT/2;
	private int charIdx;
	private int lineIdx;
	private int refreshIdx;
	private ArrayList<KeyListener> ListenerList;
	private MessageKeys keys;
	private String[] currentMessages;
	private int hurry;
	private boolean dismiss;
	private String text;
	private BufferedImage background;
	private ArrayList<KeyListener> keylisteners = new ArrayList<KeyListener>();
	
	public Message(String[] strings) {
		currentMessages = strings;
		keys = new MessageKeys(this);
		keylisteners.add(keys);
	}
	

	
	public void drawMessage(Graphics2D gffx,Boolean _bottom){
		if(refreshIdx != PREFS.MESSAGESPEED){
			refreshIdx++;
		}else if(lineIdx != -1){
			incrementMessageIdx();
			refreshIdx = 0;
		}
		String toShow = "";
		gffx.setFont(gffx.getFont().deriveFont(25f));
		bottom = 0;
		if(_bottom){
			bottom = PREFS.CANVASHEIGHT/2;
		}
		int i;
		for(i = 0; i< lineIdx; i++){
			gffx.drawString(currentMessages[i], PREFS.MESSAGEPADDINGX, bottom+PREFS.MESSAGEPADDINGY+i*PREFS.LINEHEIGHT);
		}
		gffx.drawString(currentMessages[lineIdx].substring(0,charIdx), PREFS.MESSAGEPADDINGX, bottom+PREFS.MESSAGEPADDINGY+i*PREFS.LINEHEIGHT);

		
	}
	public void incrementMessageIdx(){
		if(charIdx >= currentMessages[lineIdx].length()){
			if(lineIdx < currentMessages.length - 1){
				lineIdx++;
				charIdx = 0;
			}else{
				dismiss = true;
			}
		}else{
			charIdx++;
		}
	}
	public void show(String[] messages){
		PREFS.DIALOGACTIVE = true;
		ListenerList = KeyController.getInstance().clearAll();
		KeyController.getInstance().registerListener(keys);
		charIdx = 0;
		lineIdx = 0;
		currentMessages = messages;
	}

	public void hurry() {

		if(dismiss){
		
			lineIdx = 0;
			charIdx = 0;
			Controller.getInstance().setScreen(Game.gameDisplay);
			dismiss = false;
			return;
		}
		
		
		if(lineIdx+1 >= currentMessages.length){
			charIdx = currentMessages[lineIdx].length();
			dismiss = true;
		}else{
			lineIdx++;
			charIdx = 0;
		}
		
	}



	public void draw(Graphics gffx, int width, int height) {
		int maxX = PREFS.GAMEWIDTH - PREFS.MESSAGEPADDINGX - 10;
		int maxY = (PREFS.GAMEHEIGHT/2) - PREFS.MESSAGEPADDINGY - 10;
		gffx.setColor(Color.black);		
		gffx.fillRoundRect(49, 49, maxX, maxY, 20, 20);		
		
		gffx.setColor(Color.WHITE);	
		gffx.fillRoundRect(50, 50, maxX-2, maxY-2, 20, 20);
		
		gffx.setColor(Color.BLUE);
		gffx.fillRoundRect(60, 60, maxX-22, maxY-22, 20, 20);

		gffx.setColor(Color.WHITE);	
		drawMessage((Graphics2D) gffx, false);
	}



	public ArrayList<KeyListener> getKeyListeners() {
		
		return keylisteners;
	}
}
