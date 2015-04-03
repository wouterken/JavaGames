package com.pico.system;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pico.*;
import com.pico.display.Frame;
import com.pico.display.Controller;
import com.pico.display.screens.GameMenu;
import com.pico.display.screens.GameScreen;
import com.pico.events.EventBus;
import com.pico.events.EventListener;
import com.pico.events.GameEvent;
import com.pico.events.LeftEvent;
import com.pico.input.AreaLoader;
import com.pico.input.ImageLoader;
import com.pico.input.ImageMap;
import com.pico.logic.Fun;
import com.pico.logic.Info;
import com.pico.logic.locations.Area;
import com.pico.objects.DrawableObject;
import com.pico.objects.WorldObject;
import com.pico.objects.characters.player.Player;
import com.pico.prefs.PREFS;
import com.pico.prefs.SHADOWS;


public class Game implements EventListener{
	 

	private static Game instance;
	public static GameScreen gameDisplay = new GameScreen();
	public static GameMenu gameMenu = new GameMenu();
	private Player player;
	private Area world;
	private boolean wop;
	private HashMap <String,String> prefs = new HashMap<String, String>();
	public Game(){
		System.setProperty("apple.awt.antialiasing", "off");
		System.setProperty("apple.awt.rendering", "speed");
		System.setProperty("apple.awt.interpolation", "nearestneighbor");
		System.setProperty("apple.awt.graphics.UseQuartz", "true");
		System.setProperty("apple.awt.graphics.EnableLazyPixelConversion", "true");

		try {

		
		player = PREFS.DEFAULTPLAYER;	
				
		
		world = AreaLoader.loadWorld(PREFS.String("startingArea"));
		
		
		gameDisplay.setWorld(world);
		 Controller.getInstance().init(gameDisplay);
		GameThread.init(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventBus.registerListener(new LeftEvent(), this);
	}
	

	




	public void setNewWorld(){
		if(wop){
			wop = false;
		DrawableObject[] obs = {
//				new WorldObject("ob1", "ob1", 10, 10, "player.png", "mask.jpg"),
//				new WorldObject("ob1", "ob1", 20, 20, "player.png", "mask.jpg"),
//				new WorldObject("ob1", "ob1", 40, 30, "player.png", "mask.jpg"),
//				new WorldObject("ob1", "ob1", 30, 40, "player.png", "mask.jpg")
				};
		//world = new Area("background.jpg","world.png","worldMask.jpg",Arrays.asList(obs));
		
		}else{
			wop = true;
			DrawableObject[] obs = {
//					new WorldObject("ob1", "ob1", 50, 50, "player.png", "mask.jpg"),
//					new WorldObject("ob1", "ob1", 150, 150, "player.png", "mask.jpg"),
//					new WorldObject("ob1", "ob1", 250, 250, "player.png", "mask.jpg"),
//					new WorldObject("ob1", "ob1", 350, 350, "player.png", "mask.jpg")
					};
		//	world = new Area("background.jpg","world.png","worldMask.jpg",Arrays.asList(obs));
			
		}
		gameDisplay.setWorld(world);
	}
	public void refresh(){
			player.refresh(world);
	}
	public static Game getInstance(){
		if(instance == null){
			instance = new Game();
		}
		return instance;
	}

	public static void main(String[] args) {
		Game.getInstance();
	}







	public void fire(GameEvent event) {
		System.out.println("left");
	}








}

