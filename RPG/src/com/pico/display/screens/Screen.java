package com.pico.display.screens;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public interface Screen {

	public void draw(Graphics gffx, int width, int height);

	public ArrayList<KeyListener> getKeyListeners();


}
