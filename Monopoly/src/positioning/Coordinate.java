package positioning;

public class Coordinate {
	private int x;
	private int y;
	/**
	 * Monopoly co-ordinate takes an x and a y between 1-11, in respect to the 40 spaces on the board.
	 */
	public Coordinate(int _x, int _y){
		x = (_x*60)-25; //calculations to make co-ordinates accurate in respect to the current board
		y = (_y*60)-25;//change here if you wish to change the board size.
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
