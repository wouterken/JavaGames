package com.pico.objects;

import java.awt.Graphics2D;
import java.awt.Image;

public interface DrawableObject {

	public Image getImage();
	public int getX();
	public int getY();
	public int getImageY();
	public Image getMask();
	public int getBottom();
	public int getWidth();
	public boolean hasShadow();
	public int overlayLevel();
	public int getHeight();
	public void activate();
}
