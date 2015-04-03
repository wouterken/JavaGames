package specialAreas;

import game.Player;
import positioning.Coordinate;
import properties.Location;

public class FreeParking extends SpecialArea implements Location {

	public FreeParking(Coordinate _coordinate){
		super(_coordinate);
	}
	public String name() {
		return "Free Parking";
	}
	public String toString(){
		return "You get free parking";
	}
	public String printOption(Player currentPlayer) {
		return null;
	}
	public String autoAction(Player currentPlayer) {
		return "Landed on free parking";
	}


}
