package com.pico.controllers;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.event.MouseInputListener;

import com.pico.display.GameCanvas;
import com.pico.input.ImageMap;
import com.pico.logic.Fun;
import com.pico.logic.Info;
import com.pico.prefs.PREFS;

public class MouseEventScaler implements MouseListener, MouseInputListener, MouseMotionListener{

	public static MouseController mouse = new MouseController();

	public void mouseDragged(MouseEvent arg0) {
		mouse.mouseDragged(setScalable(arg0));
	}
	


	public void mouseMoved(MouseEvent arg0) {
		mouse.mouseMoved(setScalable(arg0));
	}

	
	public void mouseClicked(MouseEvent arg0) {
		mouse.mouseClicked(setScalable(arg0));
	}

	
	public void mouseEntered(MouseEvent arg0) {
		mouse.mouseEntered(setScalable(arg0));
	}

	
	public void mouseExited(MouseEvent arg0) {
		mouse.mouseExited(setScalable(arg0));
	}

	
	public void mousePressed(MouseEvent arg0) {
		mouse.mousePressed(setScalable(arg0));
	}

	
	public void mouseReleased(MouseEvent arg0) {
		mouse.mouseReleased(setScalable(arg0));
	}
	
	public static MouseEvent setScalable(MouseEvent m) {
		ScalableMouseEvent e = new ScalableMouseEvent(m);
		double widthMult = ((double)PREFS.CANVASWIDTH / (double)PREFS.GAMEWIDTH);
		double heightMult = ((double)PREFS.CANVASHEIGHT / (double)PREFS.GAMEHEIGHT);
		double newX = m.getX() / widthMult;
		double newY = (m.getY()-10) / heightMult;
		e.setPoint(new Point((int) newX, (int) newY));
		return e;
	}
}
