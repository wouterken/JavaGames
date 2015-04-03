package display;
import gamelogic.World;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import prefs.PRFS;


public class GameCanvas extends JComponent{

	public static boolean drawing;
	public static boolean repainting;
	public Paintable currentScreen;
	

	public GameCanvas(){
		super();
		setPreferredSize(new Dimension(600,600));
		repaint();
	}

	@Override
	protected void paintComponent(Graphics gffx) {
		//System.out.println("Entered~");

			currentScreen.draw(gffx);
			

	}

	public void setPaintable(Paintable screen){
		this.currentScreen = screen;
	}

}
