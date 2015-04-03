package com.pico.prefs;

import java.awt.Image;

import com.pico.input.ImageLoader;

public class SHADOWS {
	public static boolean ON = PREFS.SHADOWS;
	public static Image IMAGE = ImageLoader.loadImage("shadow.png");
	public static int WIDTH = IMAGE.getWidth(null);
	public static int HEIGHT = IMAGE.getHeight(null);
	public static double MULT = (double)HEIGHT/WIDTH;
	
}
