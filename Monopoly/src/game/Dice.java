package game;

import gui.GameCanvas;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Dice {
	private int lastDiceRoll = 0;
	private static final Image one = loadImage("1.png");
	private static final Image two = loadImage("2.png");
	private static final Image three = loadImage("3.png");
	private static final Image four = loadImage("4.png");
	private static final Image five = loadImage("5.png");
	private static final Image six = loadImage("6.png");
	private static final String IMAGE_PATH = "images/dice/";
	private static int animatorIndex = 0;
	
	private static int die1;
	private static int die2;
	/**
	 * Rolls two dice, returns the sum of their results.
	 * @return DiceRoll
	 */
	public  int rollDice() {
		 die1 = (int)(6*Math.random() + 1);
		 die2 = (int)(6*Math.random() + 1);
		lastDiceRoll = die1+ die2;
		return lastDiceRoll;
	}
	public static Image dieOne(){
		if(die1 == 1){
			return one;
		}
		else if(die1 == 2){
			return two;
		}
		else if(die1 == 3){
			return three;
		}
		else if(die1 == 4){
			return four;
		}
		else if(die1 == 5){
			return five;
		}
		else
			return six;
		
	}
	public static Image dieTwo(){
		if(die2 == 1){
			return one;
		}
		else if(die2 == 2){
			return two;
		}
		else if(die2 == 3){
			return three;
		}
		else if(die2 == 4){
			return four;
		}
		else if(die2 == 5){
			return five;
		}
		else
			return six;
		
	}
	public int lastDiceRoll(){
		return lastDiceRoll;
	}
	/**
	 * for testing puproses, causes dice to roll an int, which is then stored in the lastDiceRoll;
	 * @param x
	 */
	public void roll(int x){
		lastDiceRoll = x;
	}
	public static Image diceRollAnimation(){
		if(animatorIndex == 0){
			animatorIndex++;return one;
		}
		if(animatorIndex == 1){
			animatorIndex++;return four;
		}
		if(animatorIndex == 2){
			animatorIndex++;return two;
		}
		if(animatorIndex == 3){
			animatorIndex++;return three;
		}
		if(animatorIndex == 4){
			animatorIndex++;return six;
		}
		if(animatorIndex == 5){
			animatorIndex=0;return five;
		}
		else return one;
		
	}
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

}
