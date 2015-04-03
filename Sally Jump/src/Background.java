import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;


public class Background {
	
	private SallyJump world;
	private Image backgroundImage = loadImage("clouds.jpg");
	private int curX;
	public Background(SallyJump world){
		this.world = world;
	}
	private Image loadImage(String string) {
		Image returnImg = null;
		try{
			File imageFile = new File(string);
		 returnImg = ImageIO.read(imageFile);
		}catch(Exception e){
			System.out.println("Error loading image");
		}
		return returnImg;
	}
	public void draw(Graphics gffx) {
		curX++;
		if(curX == 400) curX = 0;
		gffx.drawImage(backgroundImage,0,curX,400,400,null);
		gffx.drawImage(backgroundImage,0,curX-400,400,400,null);
	}
}
