package properties;

import positioning.Coordinate;
import game.Player;

public interface Location {
	/**
	 * The autoAction gets completed every time a player lands on this location.
	 * e.g go-to jail, get a card, pay rent.
	 * @param currentPlayer
	 */
	String autoAction(Player currentPlayer);
	public String name();
	public Coordinate getCoordinates();
	/**
	 * Used by the board to ensure it prints relevant choices in the text based menu.
	 * If it returns null the info is not printed.
	 * If it returns a string, the string is printed and becomes an option in the text based menu.
	 * @param currentPlayer
	 * @return infoString
	 */
	String printOption(Player currentPlayer);
	
	

}
