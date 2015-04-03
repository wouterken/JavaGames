package specialAreas;

import positioning.Coordinate;

/**
 * Empty interface to separate locations.
 *
 */
public abstract class SpecialArea {
	protected Coordinate coordinate;
	public SpecialArea(Coordinate _coordinate){
		coordinate = _coordinate;
	}
	public Coordinate getCoordinates(){
		return coordinate;
	}
	
}
