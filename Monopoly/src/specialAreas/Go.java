package specialAreas;

import game.Player;
import positioning.Coordinate;
import properties.Location;

public class Go extends SpecialArea implements  Location{

	public Go(Coordinate _coordinate){
		super(_coordinate);
	}
	public String toString(){
		return "You are on go and collect $200";
	}
	public String name() {
		return "Go!";
	}
	public String printOption(Player currentPlayer) {
		return null;
	}
	public String autoAction(Player currentPlayer) {
		return "";
		
	}

}
