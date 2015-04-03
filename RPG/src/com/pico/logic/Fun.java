package com.pico.logic;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Fun {
	
	private static Graphics _gffx;
	
	public static void setGraphics(Graphics gffx){
		_gffx = gffx;
	}
	
	public static void draw(BufferedImage sprite){
		do{
			if(_gffx == null) break;
			
			_gffx.drawImage(sprite, 0, 0, null);
		}while(false);
	}
	public static void draw(BufferedImage sprite, Point location){
		do{
			if(_gffx == null) break;	
			_gffx.drawImage(sprite, location.x, location.y, null);
		}while(false);
	}
	public static void draw(BufferedImage sprite, Point location, Point size){
		do{
			if(_gffx == null) break;
			
			_gffx.drawImage(sprite, 
					location.x, 
					location.y, 
					location.x + size.x,
					location.y + size.y,
					0,
					0,
					sprite.getWidth(),
					sprite.getHeight(),
					null
					);
			
		}while(false);
	}
}
