package gamelogic.entities;
import gamelogic.Block;
import gamelogic.GameKeyController;
import gamelogic.World;
import gamelogic.GameKeyController.direction;
import gamelogic.weapons.Gun;
import gamelogic.weapons.Projectile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import display.ImageHandler;

import prefs.PRFS;


public class Player extends Entity {

	//Player name
	private String name;
	
	//Player Refresh Speed
	private int fps = 10;
	private int frmNum = 0;

	
	//Player gun/angle/animation
	private double angle = 90;
	private Gun gun;

	private int shot;

	private boolean bouncyBullets;


	
	public Player(String name,int x, int y) {
		super(x, y, 60, 60,ImageHandler.split(3,"walk.png"),false);
		this.name = name;
		this.gun = new Gun();
		this.weight = 12;
		entitySpeed = 1;
	}
	
	public void draw(Graphics paintBrush) {
		if(PRFS.frmNum == 0)checkWalkIndex();
		if(facingLeft){
		paintBrush.drawImage(walkImages[walkIndex], 300, 300,width,height,null);
		}else{
			Image img = walkImages[walkIndex];
			paintBrush.drawImage(img,300,300,300+width,300+height ,img.getWidth(null), 0,0,img.getHeight(null),null);	
		}
		gun.draw(facingLeft,angle,paintBrush,walkIndex == ((int)walkImages.length/2));
	}
	
	

	public void shoot() {
		
		World.usingProjectiles = true;
		if(fps == frmNum){
		World.projectiles.add(new Projectile(angle,position.x,position.y,facingLeft,bouncyBullets));
		frmNum = 0;
		}
		else frmNum++;
		World.usingProjectiles = false;
	}

	public void lookUp() {
		if(angle >30){
		angle-=5;
		}
	}
	public void lookDown() {
		if(angle < 150){
			angle+=5;
		}
	}
	public void slowDownLeftRightMovement(){
			if(groundSpeed >0){
				facingLeft = true;
				groundSpeed -= 0.2;
			}
			else{
				facingLeft = false;
				groundSpeed += 0.2;
			}
			
		}

	public void changeBullets() {
		System.out.println("Switch");
		if(PRFS.fps == PRFS.frmNum){
		bouncyBullets = !bouncyBullets;
		}
		else PRFS.frmNum++;
	}
	
		

}
