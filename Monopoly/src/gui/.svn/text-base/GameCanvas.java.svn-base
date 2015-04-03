package gui;

// This file is part of the Multi-player Pacman Game.
//
// Pacman is free software; you can redistribute it and/or modify 
// it under the terms of the GNU General Public License as published 
// by the Free Software Foundation; either version 3 of the License, 
// or (at your option) any later version.
//
// Pacman is distributed in the hope that it will be useful, but 
// WITHOUT ANY WARRANTY; without even the implied warranty of 
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See 
// the GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public 
// License along with Pacman. If not, see <http://www.gnu.org/licenses/>
//
// Copyright 2010, David James Pearce. 


import game.Board;
import game.Dice;
import game.Player;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;


/**
 * The board canvas is responsible for drawing the game. Currently, it uses a
 * relatively primitive form of double buffering to ensure there's no flicker
 * during frame updates. This class also generates a number of images using
 * Java's graphics capabilities, which saves having to have lots of very similar
 * images for the different directions.
 * 
 * @author djp
 * 
 */
public class GameCanvas extends Canvas {
	private static final String IMAGE_PATH = "images/";

	private static final Image Board = loadImage("Board.png");
	
	private static int monopolyBoardSize = 700;
	private static int boardWidth = monopolyBoardSize + 400;
	private static int boardHeight = monopolyBoardSize + 100;
	private static int diceY = monopolyBoardSize - 230;
	private static int diceX = monopolyBoardSize -340;
	private static String message;

	
	private Player currentPlayer;
	private Board board;

	private static final String[] preferredFonts = { "Courier New", "Arial",
			"Times New Roman" };
	private Font font;


	public GameCanvas(Board _board) {
		board = _board;
		setBackground(Color.GRAY);
		currentPlayer = null;
		GraphicsEnvironment env = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		HashSet<String> availableNames = new HashSet();

		for (String name : env.getAvailableFontFamilyNames()) {
			availableNames.add(name);
		}

		for (String pf : preferredFonts) {
			if (availableNames.contains(pf)) {
				font = new Font(pf, Font.BOLD, 24);
				break;
			}
		}
		setSize(new Dimension(boardWidth,boardHeight));
	
	}

	public void paint(final Graphics g) {
		
		g.drawImage(Board, 0, 0, null, null);
		board.drawItems(g);
		currentPlayer = board.getCurrentPlayer();
		if(currentPlayer == null){
		drawTitle("Welcome to Monopoly!",g);
		drawInfo("Press space to begin!",g);
		drawMessage("Press space to start!", g);
		}else{
			drawTitle(currentPlayer.name(),g);
			drawActionInfo(board.getActionInfo(),g);
			drawInfo(board.getInfo()+"\n\n\n"+currentPlayer.getInfo(),g);
			drawMessage("Press [space] to finish your turn", g);
			drawMoney(currentPlayer,g);
		}
		message = board.getCurrentMessage();
		drawOptions(message,g);
	
		g.drawImage(Dice.dieOne(), diceX, diceY, null, null);
		g.drawImage(Dice.dieTwo(), diceX + 100, diceY, null, null);
		
		}
	
	private void drawMoney(Player currentPlayer, Graphics g) {
		String money = "$"+currentPlayer.totalMoney();
		int x = boardWidth - 150;
		int y = 50;
		g.setColor(Color.YELLOW);
		float size = 35;
		Font newFont = font.deriveFont(size);
		g.setFont(newFont);
		char[] chars = money.toCharArray();
		g.drawChars(chars,0,chars.length, x, y);
	}


		
		
		

	public void repaint(Graphics g){
		paint(g);
	}
	private void drawTitle(String msg, Graphics g) {
		float size = 25;
		Font newFont = font.deriveFont(size);
		g.setFont(newFont);
		g.setColor(Color.BLACK);
		char[] chars = msg.toCharArray();
		int x = monopolyBoardSize + 60;
		int y = 35;
		
			g.drawChars(chars,0,chars.length, x, y);
			
		}
	private void drawInfo(String msg, Graphics g) {
		float size = 15;
		Font newFont = font.deriveFont(size);
		g.setFont(newFont);
		g.setColor(Color.BLACK);
		char[] chars = msg.toCharArray();
		int x = monopolyBoardSize+60;
		int y = 60;
		
		

		int i;
		int leftIdx;
		int rightIdx = 0;
		for(i = 0; i< chars.length; i=rightIdx){
			for(rightIdx = i+1; rightIdx < chars.length; rightIdx++){
				if(chars[rightIdx] == '\n') break;
			}
			g.drawChars(chars, i, rightIdx - i, x, y);
			y = y+15;
		}
		
	}
	private void drawActionInfo(String msg, Graphics g) {
		float size = 15;
		Font newFont = font.deriveFont(size);
		g.setFont(newFont);
		g.setColor(Color.BLACK);
		char[] chars = msg.toCharArray();
		int x = monopolyBoardSize+60;
		int y = 475;
		
		

		int i;
		int leftIdx;
		int rightIdx = 0;
		for(i = 0; i< chars.length; i=rightIdx){
			for(rightIdx = i+1; rightIdx < chars.length; rightIdx++){
				if(chars[rightIdx] == '\n') break;
			}
			g.drawChars(chars, i, rightIdx - i, x, y);
			y = y+15;
		}
		
	}
	private void drawOptions(String msg, Graphics g) {
		float size = 12;
		Font newFont = font.deriveFont(size);
		g.setFont(newFont);
		g.setColor(Color.BLACK);
		char[] chars = msg.toCharArray();
		int x = monopolyBoardSize+60;
		int y = monopolyBoardSize-140;
		g.setColor(new Color(255,255,150));


		int i;
		int leftIdx;
		int rightIdx = 0;
		for(i = 0; i< chars.length; i=rightIdx){
			for(rightIdx = i+1; rightIdx < chars.length; rightIdx++){
				if(chars[rightIdx] == '\n') break;
			}
			g.drawChars(chars, i, rightIdx - i, x+20, y+20);
			y = y+15;
		}
		
		offscreen = null; // reset offscreen, since we want to get rid of the
		
	}




	private void drawMessage(String msg, Graphics g) {
		float size = 20;
		Font newFont = font.deriveFont(size);
		g.setFont(newFont);
		g.setColor(Color.BLUE);
		char[] chars = msg.toCharArray();
		int x = 0;
		int y = monopolyBoardSize+50;
		g.drawChars(chars, 0, chars.length, x + 15, y + 20);
		offscreen = null; // reset offscreen, since we want to get rid of the
		
	}

	private Image offscreen = null;


	public void update(Graphics g) {
		if (offscreen == null) {
			initialiseOffscreen();
		}
		Image localOffscreen = offscreen;
		Graphics offgc = offscreen.getGraphics();
		// do normal redraw
		paint(offgc);
		// transfer offscreen to window
		g.drawImage(localOffscreen, 0, 0, this);
	}

	private void initialiseOffscreen() {
		Dimension d = size();
		offscreen = createImage(d.width, d.height);
		// clear the exposed area
		Graphics offgc = offscreen.getGraphics();
		offgc.setColor(getBackground());
		offgc.fillRect(0, 0, d.width, d.height);
		offgc.setColor(getForeground());




	}




	/**
	 * Load an image from the file system, using a given filename.
	 * 
	 * @param filename
	 * @return
	 */
	public static Image loadImage(String filename) {
		// using the URL means the image loads when stored
		// in a jar or expanded into individual files.
		java.net.URL imageURL = GameCanvas.class.getResource(IMAGE_PATH
				+ filename);

		try {
			Image img = ImageIO.read(imageURL);
			return img;
		} catch (IOException e) {
			// we've encountered an error loading the image. There's not much we
			// can actually do at this point, except to abort the game.
			throw new RuntimeException("Unable to load image: " + filename);
		}
	}

	public void setCurrentPlayer(Player _currentPlayer) {
		currentPlayer = _currentPlayer;
		
	}
}
