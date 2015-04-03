package com.pico.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import com.pico.display.Controller;
import com.pico.display.screens.Message;
import com.pico.input.ImageLoader;
import com.pico.prefs.PREFS;
import com.pico.prefs.SHADOWS;

public class WorldObject implements DrawableObject{

	private String name;
	private String description;
	public int x;
	public int y;
	public int overlayLevel;
	
	public BufferedImage image;
	private Rectangle boundingBox;
	public BufferedImage mask;
	private int height;
	public int getHeight() {
		return height;
	}
	private int width;
	public boolean hasShadow;

	public WorldObject(String _name, String _description, int _x, int _y, int _width, int _height, String _imageName,String _maskName, boolean _hasShadow,int _overlayLevel) {
		name = _name;
		description = _description;
		
		width = _width;
		height = _height;
		hasShadow = _hasShadow;
		
		initImage(_imageName);
		initMask(_maskName);
		x = _x;
		y = _y - height + image.getHeight();
		overlayLevel = _overlayLevel;
		
	}
	public void initMask(String _maskName){
		
		Image baseImage = ImageLoader.loadImage(_maskName);
		int baseWidth  = baseImage.getWidth(null);
		int baseHeight = baseImage.getHeight(null);
		
		
		mask = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics gffx = mask.createGraphics();
		gffx.drawImage(baseImage, 0, 0, width, height, 0, 0, baseWidth, baseHeight, null);

	}
	public void initImage(String _imgName){
		
		image = ImageLoader.loadImage(_imgName,width);
		
	}

	

	public Image getImage() {
		return image;
	}

	public Image getMask() {
		return mask;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getImageY() {
		return y - image.getHeight() + height;
	}
	@Override
	protected void finalize() throws Throwable {
		image.flush();
		super.finalize();
	}

	public int getBottom() {
		return y + mask.getHeight();
	}

	public int getWidth() {
		return width;
	}
	public boolean hasShadow() {
		return hasShadow;
	}
	public int overlayLevel() {
		
		return overlayLevel;
	}
	public void activate() {

		Controller.setScreen(new Message(concat(new String[]{name+":"}, description.split("\\\\n"))));
	}
	public static <T> T[] concat(T[] first, T[] second) {
		  T[] result = Arrays.copyOf(first, first.length + second.length);
		  System.arraycopy(second, 0, result, first.length, second.length);
		  return result;
		}


}
