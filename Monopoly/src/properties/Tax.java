package properties;

import positioning.Coordinate;
import game.Player;




public class Tax implements Location {

	int amount;
	String name;
	private Coordinate coordinate;
	public Tax(String _name,int _amount, Coordinate _coordinate){
		name = _name;
		amount = _amount;
		coordinate = _coordinate;
	}
	

	public String name() {
		return name;
	}
	public String printOption(Player currentPlayer) {
		return null;
	}
	public String autoAction(Player currentPlayer){
		currentPlayer.deduct(amount);
		return " paid $"+amount+"  in tax.";
	}
	public String toString(){
		return "Tax";
	}
	public Coordinate getCoordinates(){
		return coordinate;
	}




}
