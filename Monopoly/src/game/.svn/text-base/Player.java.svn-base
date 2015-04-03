package game;

import gui.Drawable;
import gui.GameCanvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


import positioning.Coordinate;
import properties.*;
import specialAreas.Go;

public class Player implements Drawable {
	private String name;


	private boolean inJail;
	private boolean isBankrupt;
	private int jailDaysToGo;
	private int money;
	private Location currentPosition;
	private String token;

	private Image tokenImage;
	private static final String IMAGE_PATH = "images/tokens/";

	public ArrayList<Property> ownedProperties = new ArrayList<Property>();
	public ArrayList<Street> ownedStreets = new ArrayList<Street>();

	public Player(String _name, String _token) {
		currentPosition = new Go(new Coordinate(11,11));
		name = _name;
		money = 1500;
		token = _token;
		tokenImage = loadImage(token+".png");
	}
	public String name() {
		// TODO Auto-generated method stub
		return name;
	}

	public void setCurrentPosition(Location position) {
		currentPosition = position;
	}

	public Location currentPosition() {
		return currentPosition;
	}

	public void add(int amount) {
		money += amount;
	}

	public void deduct(int amount) {

		money -= amount;
		if (money <= 0) {
			isBankrupt = true;
			money = 0;
			System.out.println(name + " is bankrupt!");
		}

	}

	public int totalMoney() {
		return money;
	}

	public boolean inJail() {
		return inJail;
	}

	/**
	 * Adds property to the players list of owned properties. If it is a street
	 * it will also add it to the players list of owned streets.
	 * 
	 * @param property
	 */
	public void addProperty(Property property) {
		ownedProperties.add(property);
		if (property instanceof Street) {
			ownedStreets.add((Street) property);
		}
	}

	public void removeProperty(Property property) {
		ownedProperties.remove(property);
		if (property instanceof Street) {
			ownedStreets.remove((Street) property);
		}
	}

	public void setInJail(boolean b) {
		inJail = b;
	}

	public void setJailTime(int t) {
		jailDaysToGo = t;
	}

	public void jailDayPassed() {
		jailDaysToGo--;
		if (jailDaysToGo == 0) {
			setInJail(false);
		}
	}

	public int jailDaysToGo() {
		return jailDaysToGo;
	}

	public boolean isBankrupt() {
		return isBankrupt;
	}

	public void checkBankrupcy() {
		if (totalMoney() < 1)
			isBankrupt = true;
	}

	public String getInfo() {
		String info = "";
		info = name + " owns :\n";
		if (ownedProperties.size() == 0)
			return info + " No Properties.";
		boolean ownsStreets = false;
		boolean ownsRailWays = false;
		boolean ownsUtilities = false;
		for (Property p : ownedProperties) {
			if (p instanceof Street) {
				ownsStreets = true;
				continue;
			}
			if (p instanceof Utility) {
				ownsUtilities = true;
				continue;
			}
			if (p instanceof Railway) {
				ownsRailWays = true;
				continue;
			}
		}
		if (ownsStreets) {
			info = info + "Streets:\n";
			info = info + getOwnedStreets();
		}
		if (ownsRailWays) {
			info = info + "Railways:\n";
			info = info + getOwnedRailways();
		}
		if (ownsUtilities) {
			info = info + "Utilities:\n";
			info = info + getOwnedUtilities();
		}
		
		
		return info;
	}

	public String getOwnedStreets() {
		String info = "";
		for (Property p : ownedProperties) {
			if(p instanceof Street){
			info = info + "  "+p.name();
					
			if (p instanceof Street) {
				info = info + ":" + ((Street) p).getStreetColor() + "";
				info = info
						+ ((((Street) p).canBuild(this)) ? "\nYou can build on here."
								: "");
			}
			info = info + ((p.isMortgaged()) ? "\n: Mortgaged.\n" : "\n"); 
			}
		}
		return info;
	}

	public String getOwnedUtilities() {
		String info = "";
		for (Property p : ownedProperties) {
			if(p instanceof Utility){
			info = info +"  "+ p.name()
					+ ((p.isMortgaged()) ? ": Mortgaged | " : " ");
			if (p instanceof Street) {
				info = info + ": Color: " + ((Street) p).getStreetColor() + "";
				info = info
						+ ((((Street) p).canBuild(this)) ? "\nYou can build on here."
								: "");
			}
			info = info + "\n";
			}
		}
		return info;
	}

	public String getOwnedRailways() {
		String info = "";
		for (Property p : ownedProperties) {
			if(p instanceof Railway){
			info = info +"  "+ p.name()
					+ ((p.isMortgaged()) ? ": Mortgaged | " : " ");
			if (p instanceof Street) {
				info = info + ": Color: " + ((Street) p).getStreetColor() + "";
				info = info
						+ ((((Street) p).canBuild(this)) ? "\nYou can build on here."
								: "");
			}
			info = info + "\n";
			}
		}
		return info;
	}
	@Override
	public void draw(Graphics g) {
		Coordinate c = currentPosition.getCoordinates();
		int x = c.getX();
		int y = c.getY();
		g.drawImage(tokenImage,x,y,null,null);
		char[] playerName = name.toCharArray();
		g.setColor(new Color(338899));
		g.drawChars(playerName, 0, playerName.length, x, y+50);
		
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
