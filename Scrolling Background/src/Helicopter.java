import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.imageio.ImageIO;


public class Helicopter {
	private Color ballColor;
	private int heliWidth;
	private int heliHeight;
	private Image Helicopter =  loadImage("helicopter.png");
	int x; 
	double y;
	private int xSpeed = 0;
	private double ySpeed = 0;
	public Helicopter(){
		this.heliWidth = 100;
		this.heliHeight= 35;
		x = 40;
		y = 350;
	}
	public void draw(Graphics gffx){
		gffx.drawImage(Helicopter,x,(int)(y),heliWidth,heliHeight,null);
	}
	private Image loadImage(String string) {
		Image returnImg = null;
		try{
			File imageFile = new File(string);
		 returnImg = ImageIO.read(imageFile);
		}catch(Exception e){
			System.out.println("Error loading image");
		}
		return returnImg;
	}
	public boolean step(){
			ySpeed+=0.03; 
			y += ySpeed;
		
		if(!(x + xSpeed <40 || x + xSpeed >700)){
		x+= xSpeed;
		return true;
		}
		return false;
	}
	public void moveRight() {
		if(xSpeed < 3){
		xSpeed++;
		}
	}
	public void moveLeft() {
		if(xSpeed > -3){
		xSpeed--;
		}
	}
	public void moveUp() {
		ySpeed-=1.5;
	}
	public void moveDown() {
		ySpeed++;
	}
	public void checkBoundaries(int width, int height) {
	
	}
	
	public int getHeight() {
		return heliHeight;
	}
	public int getWidth() {
		return heliWidth;
	}
	public Rectangle getBoundingBox() {
		return new Rectangle(x,(int)(y),heliWidth,heliHeight);
	}
	public int getXSpeed() {
		return xSpeed;
	}
}
