package specialAreas;

import game.Board;
import game.Player;

import java.util.HashMap;

import positioning.Coordinate;
import properties.Location;


public class Jail extends SpecialArea implements Location {

	private int countdown;
	private Board board;
	private String statusString = "";
	
	public Jail(Board _board,Coordinate _coordinate){
		super(_coordinate); 
		board = _board;
	}
	public String name() {
		return "Jail";
	}
	public String toString(){
		return statusString;
	}
	public String printOption(Player currentPlayer) {
		return null;
	}
	
	/**
	 * Checks to see if the player has been sent to jail or is just visiting. If they have been sent, it will subtract one day from their sentence.
	 */
	public String autoAction(Player currentPlayer) {
		if(currentPlayer.inJail()){
			currentPlayer.jailDayPassed();
			statusString = currentPlayer.jailDaysToGo()+" turns to go in jail.";
		}
		else statusString = "Jail\nJust visiting!";
		return "";
	}


}
