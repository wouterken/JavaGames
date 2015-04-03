package gamelogic;

import gamelogic.entities.Entity;
import gamelogic.entities.Player;
import gamelogic.weapons.Projectile;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import prefs.PRFS;

import display.GameCanvas;
import display.ImageHandler;
import display.Paintable;

public class World implements Paintable {

	/**Current player in this world
	 * 
	 */
	public Player currentPlayer;
	/**
	 * All non-map entities in this world
	 */
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	/**
	 * All non-map entities visible on the current screen
	 */
	private ArrayList<Entity> visibleEntities = new ArrayList<Entity>();
	/**
	 * World background image
	 */
	public Image background = ImageHandler.loadImage("Summit.jpg");
	/**
	 * All part-of-map blocks
	 */
	private ArrayList<Block> blocks = new ArrayList<Block>();
	/**
	 * All part-of-map blocks visible on the current screen
	 */
	public static ArrayList<Block> visibleBlocks = new ArrayList<Block>();
	/**
	 * All current projectiles traveling within the world
	 */
	public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	/**
	 * Size of a block in the map.
	 */
	private static int blockSize = 100;

	private char[][] walls = PRFS.defaultWalls;
	
	/**
	 * Co-ordinates of the center of the current view point.
	 */
	private Point currentCenter;
	public static boolean usingProjectiles = false;
	
	
	/**
	 * Creates a new World from the map given.
	 * @param constructer
	 */
	public World(Map constructer) {
		if (constructer == null) {
			return;
		}
		else{
			walls = constructer.getData();
		}
		fillBlocks();
	}
	/**
	 * Creates all the blocks in the current map, and stores them in an array.
	 */
	private void fillBlocks() {
		for(int i = 0; i< walls[0].length; i++){
			for(int j = 0; j<walls.length; j++){
				switch(walls[i][j]){
				case 'g':
					blocks.add(new Block(BlockType.PLATFORM, j*blockSize, i*blockSize, blockSize, blockSize,false));
					continue;
				case '1':
					blocks.add(new Block(BlockType.BLOCK, j*blockSize, i*blockSize, blockSize, blockSize,true));
					continue;
				}
				
			}
		}
	}

	/**
	 * Sets the current player.
	 * @param player
	 */
	public void addPlayer(Player player, Point position) {
		currentPlayer = player;
		if(position == null){
			currentPlayer.setPosition(new Point(PRFS.GameWidth/2, PRFS.GameHeight/2));
		}
		else currentPlayer.setPosition(position);
	}
	public void addEntity(Entity ent){
		entities.add(ent);
	}

	private void drawProjectiles(Graphics paintBrush) {
		while(usingProjectiles){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		usingProjectiles = true;
		ArrayList<Projectile> toRemove = new ArrayList<Projectile>();
		try{
		for(Projectile p: projectiles ){
			boolean remove = p.draw(paintBrush, currentPlayer.getPosition());
			if(remove) toRemove.add(p);
			
		}
		}catch(ConcurrentModificationException e){
			usingProjectiles = false;
			System.err.println(e + "in draw projectiles");
			}
		projectiles.removeAll(toRemove);
		usingProjectiles = false;
	}

	public void draw(Graphics paintBrush) {
		refresh();
		//draw background
		paintBrush.drawImage(background,0,0,PRFS.GameWidth,PRFS.GameHeight,null);
		//draw visible blocks
		for(Block b: visibleBlocks){
			b.draw(paintBrush, currentCenter.x-300, currentCenter.y-300);
		}
		//draw entities
		for(Entity ent: visibleEntities){
			ent.draw(paintBrush,currentCenter.x-300,currentCenter.y-300);
			ent.refresh(this);
		}
		//draw player
		currentPlayer.draw(paintBrush);
		//draw projectiles.
		drawProjectiles(paintBrush);		
	}


	private void getVisibleBlocks(int i, int j) {
		visibleBlocks.clear();
		for(Block b: blocks){
			if(b.isVisible(i,j)){
				visibleBlocks.add(b);
			}
		}
	}
	private void getVisibleEntities(int i, int j) {
		visibleEntities.clear();
		for(Entity e: entities){
			if(e.isVisible(i,j)){
				visibleEntities.add(e);
			}
		}
	}

	private void refresh() {
		try{
		//Determine where current screen is, and what is visible from this point
		currentCenter = currentPlayer.getPosition();
		getVisibleBlocks(currentCenter.x-300, currentCenter.y-300);
		getVisibleEntities(currentCenter.x-300, currentCenter.y-300);
		
		checkFPS();
		//Check if any projectiles are hitting blocks or entities
		ArrayList<Projectile> toRemove = new ArrayList<Projectile>();
		try{
			while(usingProjectiles){
				Thread.sleep(10);
			}
			usingProjectiles = true;
		for(Projectile p: projectiles){
			p.clearToHit();
			p.bounce = false;
			p.switchedLeftRight = false;
			p.switchedUpDown = false;
			for(Block b: visibleBlocks){
			if(p.collidingWith(b.getBoundingBox())){
				boolean remove = b.hit(p);
				if(remove)toRemove.add(p);
			}
			}
			for(Entity e: visibleEntities){
				if(p.collidingWith(e.getBoundingBox())){
					e.hit(p);
					toRemove.add(p);
				}
			}
			p.bounce();
		
	}
		usingProjectiles= false;
		}
		catch(ConcurrentModificationException e){
			usingProjectiles= false;
			System.err.println(e);}
		projectiles.removeAll(toRemove);
		}catch(Exception e){
			usingProjectiles= false;
			System.err.println(e);
		}
		}

	public boolean checkFPS() {
		if(PRFS.fps == PRFS.frmNum){
			PRFS.frmNum = 0;
			return true;
		}else{
			PRFS.frmNum++;
			return false;
		}
	}
	public ArrayList<Block> getBlocks() {
		return blocks;
	}

	public ArrayList<Entity> getEntities(Entity thisEntity) {
		ArrayList<Entity> entitiesWithoutThis = new ArrayList<Entity>();
		entitiesWithoutThis.addAll(entities);
		entitiesWithoutThis.remove(thisEntity);
		entitiesWithoutThis.add(currentPlayer);
		return entitiesWithoutThis;
	}

}
