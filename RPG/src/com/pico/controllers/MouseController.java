package com.pico.controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.event.MouseInputListener;

import com.pico.input.ImageMap;
import com.pico.logic.Fun;
import com.pico.logic.Info;

public class MouseController implements MouseListener, MouseInputListener, MouseMotionListener{

	private static MouseController instance;


	public static MouseController getInstance(){
		if(instance == null){
			instance = new MouseController();
		}
		return instance;
	}
	public void mouseDragged(MouseEvent arg0) {
		
	}


	public void mouseMoved(MouseEvent arg0) {
	
	}

	
	public void mouseClicked(MouseEvent arg0) {
		
	}

	
	public void mouseEntered(MouseEvent arg0) {

	}

	
	public void mouseExited(MouseEvent arg0) {

	}

	
	public void mousePressed(MouseEvent arg0) {

	}

	
	public void mouseReleased(MouseEvent arg0) {
	
	}
	

}
