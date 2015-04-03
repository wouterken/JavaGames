package com.pico.controllers;



import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 * Extension of the MouseEvent class used to scale any mouse events received on a GameDisplay that has been resized,
 * onto their respective positions on the original size GameDisplay. This ensures mouse events still stay accurate
 * no matter what size the display currently is.
 * 
 * @author Wouter Coppieters (300152592)
 */
public class ScalableMouseEvent extends MouseEvent {


	private static final long serialVersionUID = 1L;
	/**
	 * The co-ordinate of the scaled - MouseEvent
	 */
	private Point coordinate;

	public ScalableMouseEvent(MouseEvent m) {
		super((Component)m.getSource(), m.getID(), m.getWhen(), m.getModifiers(), m.getX(), m.getY(), m.getClickCount(), m.isPopupTrigger());
	}

	public Point getPoint() {
		return coordinate;
	}

	public int getX() {
		return coordinate.x;
	}

	public int getY() {
		return coordinate.y;
	}
	/**
	 * Used to change the co-ordinates of a MouseEvent.
	 * @param p
	 */
	public void setPoint(Point p){
		coordinate = p;
	}
}

