package specialAreas;

import game.Board;
import game.Player;

import java.util.HashMap;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import positioning.Coordinate;
import properties.Location;

public class GotoJail extends SpecialArea implements Location {
	
	private HashMap<Player, Integer> playerPositions;

	public GotoJail(HashMap<Player, Integer> _playerPositions,Coordinate _coordinate){
		super(_coordinate);
		playerPositions = _playerPositions;

	}
	public String name() {
		return "Go to jail";
	}
	public String toString(){
	return"You are sent to jail";
	}
	public String printOption(Player currentPlayer) {
		return null;
	}
	
	/**
	 * Moves the player to jail, and sets how long they must wait there
	 */
	public String autoAction(Player currentPlayer) {
		//Look at the board class to find the jail idx. In this board it is at idx 10.
		playerPositions.put(currentPlayer, 10);
		currentPlayer.setInJail(true);
		currentPlayer.setJailTime(3);
		return currentPlayer.name()+" is sent to jail.";
	}
	


}
