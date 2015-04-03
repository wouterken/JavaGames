import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;

import javax.imageio.ImageIO;


public class PlayerBlock {
	
	private int height;
	private int width;
	private int x = GameCanvas.width/2;
	private int y = GameCanvas.height/2;
	private Rectangle boundingBox;
	public PlayerBlock(int x, int y) {
		width = x;
		height = y;
		boundingBox = new Rectangle(x,y,width,height);
	}
	


	public void jump() {
		System.out.println("Jumped");
	}

	public void move(Point point) {
		x = point.x - width/2;
		y = point.y - height/2;
		boundingBox = new Rectangle(x,y,width,height);
	}
	public Rectangle getBoundingBox(){
		return boundingBox;
	}

}
