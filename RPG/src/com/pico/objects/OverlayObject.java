package com.pico.objects;

import java.awt.Image;

public class OverlayObject implements DrawableObject {

	private final int overlayLevel;
	private final Image image;

	public OverlayObject(Image _image, int _overlayLevel){
		this.image = _image;
		this.overlayLevel = _overlayLevel;
		
	}
	public Image getImage() {
		return this.image;
	}

	public int getX() {
		return 0;
	}

	public int getY() {
		return 0;
	}

	public int getImageY() {
		return 0;
	}

	public Image getMask() {
		return null;
	}

	public int getBottom() {
		return 0;
	}

	public int getWidth() {
		return 0;
	}

	public boolean hasShadow() {
		return false;
	}

	public int overlayLevel() {
		return this.overlayLevel;
	}
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void activate() {
		// TODO Auto-generated method stub
		
	}

}
