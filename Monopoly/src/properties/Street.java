package properties;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import positioning.Coordinate;
import game.Player;
import gui.Drawable;
import gui.GameCanvas;

public class Street extends Property implements Drawable {
	
	private String colorGroup;
	
	private int rent;
	private int houses = 0; //0-6, where 6 = hotel;
	private Coordinate coordinate;
	private Street neighbour1;
	private Street neighbour2;
	private boolean hasNeighbours;
	private static final String IMAGE_PATH = "images/houses/";
	
	public Side streetSide;
	public enum Side{EAST,WEST,SOUTH,NORTH};

	public Street(String _name, int _cost, int _rent, Side _streetSide,Coordinate _coordinate) {
		super(_name, _cost,_coordinate);
		rent = _rent;
		streetSide = _streetSide;
	}


	public String buyProperty(Player currentPlayer) {
		if(owner == null){
			return buy(currentPlayer);
		}
		else
			return "This property is already owned";
	}
	public String buyHouse(Player currentPlayer){
		
		if(owner != currentPlayer){
			return"You cannot build on this street. \nThis street is owned by "+owner.name();

		}
		if(houses == 6){
			return"The street is full, You cannot \nbuild any more in this street!";

		}
		if(!canBuild(currentPlayer)){
			return"You cannot build on this street.\n You must own all the streets \nof its color first!";
	
		}
		else{
		houses++;
		if(houses == 6){
		return name+ " now has a hotel on it";
			}
		else return name+ " now has "+houses+" \nhouses on it.";
		}
		
		
		
	}
	public boolean canBuild(Player currentPlayer){
		boolean canBuild = true;
		do{
			if(neighbour1 != null){//if there is a neighbour property..
				if(neighbour1.getOwner() != this.getOwner()){//but the player doesn't own it, he can't build
					canBuild = false;
					break;
				}
			}
			if(neighbour2 != null){//same as above
				if(neighbour2.getOwner() != this.getOwner()){
					canBuild = false;
					break;
				}
			}
			if(owner != currentPlayer){//if the player doesn't own this property he also cannot build
				canBuild = false;		
				break;
			}
		if(houses == 6){//Street has a hotel on it
			canBuild = false;
		}
		}while(false);
		
		return canBuild;
	}

	
	public String payRent(Player currentPlayer) {
		int toPay = 0;
		if (owner == null) return "";	//dont pay if nobody owns the property
		if(owner == currentPlayer) return "";//don't pay if you own the property
		if(owner.inJail())return "";//dont pay if the owner is in jail
		if(isMortgaged)return "";// dont pay if property is mortgaged
		
		//***Otherwise**
			toPay += rent;
			if(houses <6){
			toPay += (houses * 25);
			}else{
			toPay += (200 * 1);}
			currentPlayer.deduct(toPay);
			owner.add(toPay);
		
		return currentPlayer.name()+" paid $"+toPay+" to "+owner.name()+".";
	}

	public String getStreetColor(){
		return colorGroup;
	}
	public boolean hasNeighbours(){
		return hasNeighbours;
	}
	/**
	 * Sets the this, and the parameters to the same color group, accepts 1-2 neighbours as parameters, Then ticks of a boolean to ensure it doesn't enter a recursive loop
	 * This is needed to see if a player can build on the street yet.
	 * @param _neighbour1
	 * @param _neighbour2
	 * @param _colorGroup
	 */
	public void setColorGroup(Street _neighbour1, Street _neighbour2, String _colorGroup) {
		neighbour1 = _neighbour1;
		neighbour2 = _neighbour2;
		colorGroup = _colorGroup;
		hasNeighbours = true;
		
		if(neighbour1 != null && !neighbour1.hasNeighbours()){//Check if this isn't null and hasn't been set already. Stops program from entering recursive loop
			neighbour1.setColorGroup(this, _neighbour2,colorGroup);
		}
		if(neighbour2 != null && !neighbour2.hasNeighbours()){//Same as above.
			neighbour2.setColorGroup(this, _neighbour1,colorGroup);
		}
		
	}
	public String printOption(Player currentPlayer) {
		if(owner == null){
		return "Buy "+ name + "($" + cost +")";}
		
		return null;
	}
	public String houses(Player currentPlayer){
		if(houses == 6){return "1 Hotel";}
		else if (houses == 0){ return "no houses.";}
		else return houses+" houses.";	
		}

	public int numberOfHouses() {
		return houses;
	}
	
	public String autoAction(Player currentPlayer){
		return payRent(currentPlayer);
		}
	public String toString(){
		String returnString = super.toString();
		returnString = returnString+"\n\n" +((houses == 6)?"1 Hotel\n":houses+" houses\n") +
				colorGroup;
		return returnString;
	}


	@Override
	public void draw(Graphics g) {
		Image house = loadImage(houses+".png");
		
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
