import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Platform {
	private Rectangle boundingBox;
	private boolean disappearing;
	
	public Platform(int x, int y){
		boundingBox = new Rectangle(x,y,100,20);
		disappearing = (((int)(Math.random()*10)>=2)?false:true);
	}
	public boolean playerIsOnTopOf(Rectangle player) {
		if(boundingBox.intersects(player)){
			if((player.y + player.height-30)<=boundingBox.y){
				return true;
			}
		}
		return false;
	}
	public void draw(Graphics gffx) {
		if(isDisappearing()){
		gffx.setColor(Color.lightGray);
		}else{
			gffx.setColor(Color.DARK_GRAY);
		}
		gffx.fillRect(boundingBox.x,boundingBox.y,boundingBox.width,boundingBox.height);
	}
	public void drop(int drop) {
		boundingBox.y += drop;
	}
	public Rectangle boundingBox(){
		return boundingBox;
	}
	public boolean isDisappearing(){
		return disappearing;
	}

}
