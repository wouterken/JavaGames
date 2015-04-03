package com.pico.objects.characters.player;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import com.pico.controllers.KeyController;
import com.pico.controllers.PlayerKeyController;
import com.pico.input.ImageLoader;
import com.pico.logic.locations.Area;
import com.pico.objects.characters.Character;
import com.pico.prefs.PREFS;
import com.pico.prefs.SHADOWS;

public class Player extends Character{

	private static Player instance;
	public int x = 30;
	public int y = 30;
	public int width = 30;
	public int height = 30;
	public int playerIdx = 0;
	public int animIdx = 0;
	public int animTimer = 0;
	public DIR animationStatus = DIR.IDLE;

	public Stack<Integer> movement = new Stack<Integer>();
	private int xDiff;
	private Image[][] images;
	private int walkAnimationSpeed;
	private int idleAnimationSpeed;
	private int idleTimeOut;
	private boolean idle;
	private int overlayLevel;
	private Area world;
	
	public Player(int _width, int _height, String _imageName,int _imgRows, int _imageCols,int _x, int _y, int _walkAnimationSpeed, int _idleAnimationSpeed,int _idleTimeOut, int _overlayLevel){
		width = _width;
		height = _height;
		x = _x;
		y = _y;
		walkAnimationSpeed = _walkAnimationSpeed;
		idleAnimationSpeed = _idleAnimationSpeed;
		idleTimeOut = _idleTimeOut;
		overlayLevel = _overlayLevel;
		initImages(_imageName,(int)((double)width*1.5),_imgRows,_imageCols);
		
		xDiff = (int)((double)(images[0][0].getWidth(null) - width)/2);
		
		KeyController.getInstance().registerListener(new PlayerKeyController(this));
	}
	public void initImages(String name, int width,int rows, int cols){
		
		images = ImageLoader.splitImage(name,width,rows,cols);
		
		
		
	}
	public static Player getInstance(int width, int height, String imageName, int imgRows, int imgCols,int x, int y, int walkAnimationSpeed, int idleAnimationSpeed, int idleTimeOut, int overlayLevel){
		if(instance == null){
			instance = new Player(width,height,imageName,imgRows,imgCols,x,y,walkAnimationSpeed,idleAnimationSpeed,idleTimeOut,overlayLevel);
		}else{
			instance.setPlayerVariables(width,height,imageName,imgRows,imgCols,x,y,walkAnimationSpeed,idleAnimationSpeed,idleTimeOut,overlayLevel);
		}
		return instance;
	}
	public static Player getInstance(){
		if(instance == null){
			instance = PREFS.getDefaultPlayer();
		}
		return instance;
	}
	
	private void setPlayerVariables(int _width, int _height, String _imageName,int _imgRows, int imgCols,int _x, int _y , int _walkAnimationSpeed, int _idleAnimationSpeed,int _idleTimeOut, int _overlayLevel) {
		width = _width;
		height = _height;
		x = _x;
		y = _y;
		overlayLevel = _overlayLevel;
		//image = (BufferedImage) ImageLoader.loadImage(_imageName);
	}
	public void draw(Graphics2D gffx){
		if(SHADOWS.ON) drawShadow(gffx);
		
		int x2 = x - xDiff;
		gffx.drawImage(images[animIdx][animationStatus.value()],x2,y - images[0][0].getHeight(null) + height,null);
	}

	public void refresh(Area _world){
		world = _world;
		updateAnimation();
		idle = true;
		if(movement.size()>0){
			idle = false;
			try{
		int keyCode = movement.peek();
		
			int curOverlayLevel = overlayLevel; 
			
			if(keyCode == KeyEvent.VK_UP){
				animationStatus = DIR.UP;
				if(canMoveUp(this.getBoundingBox(),world))
					world.moveUp(this);
			}else if(keyCode == KeyEvent.VK_DOWN){
				animationStatus = DIR.DOWN;
				if(canMoveDown(this.getBoundingBox(),world))
					world.moveDown(this);
			}else if(keyCode == KeyEvent.VK_LEFT){
				animationStatus = DIR.LEFT;
				if(canMoveLeft(this.getBoundingBox(),world))
					world.moveLeft(this);
			}else if(keyCode == KeyEvent.VK_RIGHT){
				animationStatus = DIR.RIGHT;
				if(canMoveRight(this.getBoundingBox(),world))
					world.moveRight(this);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		}
		
	}
	
	private boolean canMoveUp(Rectangle boundingBox, Area world){
		if(world.canMoveUp(boundingBox)) return true;
		else{
			int level = world.getLevel();
			if(world.switchUpLevel(level) && world.canMoveUp(boundingBox)){
				overlayLevel++;
				return true;
			}
			else if(world.switchDownLevel(level) && world.canMoveUp(boundingBox)){
				overlayLevel--;
				System.out.println("moved down!");
				return true;
			}else{
				world.setLevel(level);
			}
		}
		return false;
	}
	private boolean canMoveDown(Rectangle boundingBox, Area world){
		if(world.canMoveDown(boundingBox)) return true;
		else{
			int level = world.getLevel();
			if(world.switchUpLevel(level) && world.canMoveDown(boundingBox)){
				overlayLevel++;
				return true;
			}
			else if(world.switchDownLevel(level) && world.canMoveDown(boundingBox)){
				overlayLevel--;
				System.out.println("moved down!");
				return true;
			}else{
				world.setLevel(level);
			}
		}
		return false;
	}
	private boolean canMoveLeft(Rectangle boundingBox, Area world){
		if(world.canMoveLeft(boundingBox)) return true;
		else{
			int level = world.getLevel();
			if(world.switchUpLevel(level) && world.canMoveLeft(boundingBox)){
				overlayLevel++;
				return true;
			}
			else if(world.switchDownLevel(level) && world.canMoveLeft(boundingBox)){
				overlayLevel--;
				System.out.println("moved down!");
				return true;
			}else{
				world.setLevel(level);
			}
		}
		return false;
	}
	private boolean canMoveRight(Rectangle boundingBox, Area world){
		if(world.canMoveRight(boundingBox)) return true;
		else{
			int level = world.getLevel();
			if(world.switchUpLevel(level) && world.canMoveRight(boundingBox)){
				overlayLevel++;
				return true;
			}
			else if(world.switchDownLevel(level) && world.canMoveRight(boundingBox)){
				overlayLevel--;
				System.out.println("moved down!");
				return true;
			}else{
				world.setLevel(level);
			}
		}
		return false;
	}
	private void updateAnimation() {
		animTimer ++;
		if(idle){
			if(animTimer > idleTimeOut){
				animationStatus = DIR.IDLE;
			}
			if(animationStatus == DIR.IDLE){
				if(animTimer >= idleAnimationSpeed){
					animTimer = 0;
					animIdx++;
				}
				if(animIdx >= images.length){
					animIdx = 0;
				}
			}else{
				animIdx = 0;
			}
		}else{	
			
			if(animTimer >= walkAnimationSpeed){
				animTimer = 0;
				animIdx++;
			}
			if(animIdx >= images.length){
				animIdx = 0;
			}
		}
	}
	public Rectangle getBoundingBox() {
		return new Rectangle(x,y,width,height);
	}
	public Image getImage() {
		return images[0][0];
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getImageY() {
		return 0;
	}
	public Image getMask() {
		return null;
	}
	public void drawReflection(Graphics2D gffx) {
		int w = images[0][0].getWidth(null);
		int h = images[0][0].getHeight(null);
		
		int x2 = x - xDiff;

		
		gffx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));  
		gffx.drawImage(images[animIdx][animationStatus.value()],x2,y + height,x2+w,y+height + h,0,h,w,0,null);
		gffx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));  
		
	}
	public void drawShadow(Graphics2D gffx) {
		int shadowHeight = (int) (width * SHADOWS.MULT/2);
		int bottom = y+height;
		gffx.drawImage(SHADOWS.IMAGE,x,bottom-shadowHeight,x+width,bottom+shadowHeight,0,0,SHADOWS.WIDTH,SHADOWS.HEIGHT,null);
	}
	public int getBottom() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getImageHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	public boolean hasShadow() {
		// TODO Auto-generated method stub
		return false;
	}
	public int overlayLevel() {
		return overlayLevel;

	}
	public void activate() {
		world.activate(this.getBoundingBox(),animationStatus);
	}
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}
	
}
