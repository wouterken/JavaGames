package com.pico.objects.characters;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import com.pico.objects.DrawableObject;
import com.pico.objects.characters.interactions.Interaction;

public abstract class Character implements DrawableObject {
	public String state;
	public String name;
	public Point size;
	public int overlayLevel;
	public ArrayList<BufferedImage> walkLeft = new ArrayList<BufferedImage>();
	public ArrayList<BufferedImage> walkRight = new ArrayList<BufferedImage>();
	public ArrayList<BufferedImage> walkUp = new ArrayList<BufferedImage>();
	public ArrayList<BufferedImage> walkDown = new ArrayList<BufferedImage>();
	public BufferedImage idling;
	public HashMap<String,Interaction> conversation = new HashMap<String, Interaction>();
}
