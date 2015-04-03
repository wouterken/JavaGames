import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Block {

	private int width;
	private int height;
	private double x;
	private double y;
	private double xSpeed = 0.3;
	private double ySpeed = 0.3;
	private Color color;
	private double yMult;
	private double xMult;
	private int switchCounter = 0;

	public Block(int x, int y) {
		this.width = x;
		this.height = y;
		this.x = (int)(Math.random()*GameCanvas.width);
		this.y = (int)(Math.random()*GameCanvas.height);
		this.color = new Color((int)(Math.random()*255), (int)(Math.random()*255),(int)(Math.random()*255));
		xSpeed *= (((Math.random()*10+1)>5)?-1:1);
		ySpeed *= (((Math.random()*10+1)>5)?-1:1);
		xMult = xSpeed;
		yMult = ySpeed;
	}

	public void step() {
		switchCounter ++;
		if(x + xSpeed + width > GameCanvas.width || x+xSpeed < 0){
			xSpeed *= -1;
		}
		if(y + ySpeed + height > GameCanvas.height || y + ySpeed < 0){
			ySpeed *= -1;
		}
		x += xSpeed;
		y += ySpeed;
		
		if(switchCounter >= 100){
			if((Math.random()*10+1)>5){ xSpeed *= -1;}
			else ySpeed *= -1;
			switchCounter = 0;
		}
		xSpeed += Math.random()/3 * xMult;
		ySpeed += Math.random()/3 * yMult;
	}
	public void draw(Graphics gffx){
		gffx.setColor(color);
		gffx.fillRect((int)x, (int)y, width, height);
	}

	public boolean isTouching(Rectangle boundingBox) {
		Rectangle block = new Rectangle((int)x,(int)y,width,height);
		if(boundingBox.intersects(block)) return true;
		return false;
	}

}
