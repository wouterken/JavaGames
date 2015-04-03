package gamelogic;
import gamelogic.entities.Enemy;
import gamelogic.entities.Entity;
import gamelogic.entities.Player;

import java.awt.Point;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import mapeditor.MapKeyController;

import prefs.PRFS;

import display.GameCanvas;
import display.GameFrame;
import display.MapEditor;
import display.Paintable;


public class Game {
	private World world;
	private Screen currentScreen = Screen.GAME;
	private GameFrame frame;
	private GameCanvas canvas;
	private GameThread theGame;
	private GameKeyController gameKeyController;
	private MapKeyController mapKeyController;
	private Player player;
	private boolean refreshing;
	private MapEditor mapEditor;
	ArrayList<Entity> entities = new ArrayList<Entity>();
	public Game(){
		
		
		
		//Gui
		 frame = new GameFrame();
		 canvas = new GameCanvas();
		 
		 //World
		 player = new Player("Pico",300,300);
		 Map worldMap = new Map("tester.map");
		 world = new World(worldMap);
		 world.addPlayer(player,new Point(300,300));
		 world.addEntity(new Enemy(400,300));
		 world.addEntity(new Enemy(300,300));
		 world.addEntity(new Enemy(200,300));
		 gameKeyController = new GameKeyController(player,this);
		 
		 //Map Editor
		 this.mapEditor = new MapEditor();
		 
		 initFrame();
	
		 theGame = new GameThread(this);
		 theGame.start();
		 
		 
	
		 
	}
	public void changeScreen(Screen screen){
		Paintable paintable = world;
		switch (screen){
		case MAPEDIT:
			paintable = mapEditor;
			canvas.removeKeyListener(gameKeyController);
			canvas.addKeyListener(mapKeyController);
			frame.removeKeyListener(gameKeyController);
			frame.addKeyListener(mapKeyController);
			currentScreen = Screen.MAPEDIT;
			break;
		case GAME:
			canvas.removeKeyListener(mapKeyController);
			canvas.addKeyListener(gameKeyController);
			frame.removeKeyListener(mapKeyController);
			frame.addKeyListener(gameKeyController);
			currentScreen = Screen.GAME;
			break;
		}
		

	}

	private void initFrame() {
		
		 frame.add(canvas);
		 frame.setSize(PRFS.GameWidth,PRFS.GameHeight);
		 frame.setResizable(false);
		 frame.setVisible(true);
		 canvas.addKeyListener(gameKeyController);
		 frame.addKeyListener(gameKeyController);
		 canvas.setPaintable(world);
	
	}

	public static void main(String[] args) {
		new Game();
	}
	public void refresh() {
		while(refreshing){
			try{Thread.sleep(10);} catch(Exception e){System.err.println(e+" in game refresh");}
		}
		refreshing = true;
		canvas.repaint();
		
		if(currentScreen == Screen.GAME){
			player.refresh(world);
			long currentTime = System.currentTimeMillis();
		
			gameKeyController.calculateKeyPresses();
		}else{
			mapKeyController.calculateKeyPresses();
		}
		refreshing = false;
	
	}
	public void resetKeyController() {
		gameKeyController = new GameKeyController(player, this);
	}
}
