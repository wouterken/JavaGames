package gamelogic.weapons;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import display.ImageHandler;

public class Gun {
	
	private Image gun = ImageHandler.loadImage("gun.png");
	private int height;
	private int width;
	public Gun(){
		this.width = 60;
		this.height = 30;
	}

	public void draw(boolean facingLeft, double angle, Graphics gffx, boolean bounce) {
		int offsetY;
		if(bounce){
			offsetY = 2;
		}
		else offsetY = 0;
		Graphics2D paintBrush = (Graphics2D) gffx;
		double angl = Math.toRadians(angle-90);
		if(!facingLeft){
		paintBrush.rotate(-angl,325,325-offsetY);
		paintBrush.drawImage(gun,275,315-offsetY,275+width,315+height-offsetY ,gun.getWidth(null), 0,0,gun.getHeight(null),null);	
		paintBrush.rotate(angl,325,325-offsetY);
		}else{
			paintBrush.rotate(angl,325,325-offsetY);
			paintBrush.drawImage(gun,325,315-offsetY,width,height,null);
			paintBrush.rotate(-angl,325,325-offsetY);
		}
	}

}
