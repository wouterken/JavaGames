import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Sally {
	private int x;
	private int y;
	private int speedLeftRight;
	private int speedUp;
	private Image sallyDown =  loadImage("sally-down.png");
	private Image sallyUp =  loadImage("sally-up.png");
	private Image sally = sallyUp;
	private int totalHeight;
	private int platformDue = 0;
	private int flapping;
	private int energy= 200;
	private int score;
	
	public Sally(int x, int y){
		this.x = x;
		this.y = y;
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

	public void jump(){
		speedUp = -20;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void step(boolean moveBackground) {
		
		if(energy<200){
			energy++;
		}
		if(onPlatform()){
			jump();
			sally = sallyDown;
		}
		if(flapping != 0){
			System.out.println(flapping%2);
			sally = ((flapping%2 == 0)?sallyUp:sallyDown);
			flapping--;
			energy -=4;
		}else{
			if(speedUp > -19 && speedUp < 0){
				sally = sallyUp;
			}
		}
			speedUp += 1;
			if(x<-20) x = 390;
			if(x>420) x = 10;
			x += speedLeftRight;
		if(!moveBackground || speedUp > 0){
			y += speedUp;
		}
		else{
			if(speedUp != 0)score++;
			dropPlatforms(speedUp);
			generatePlatforms();
			}
		totalHeight += speedUp;
		}
		
	private void generatePlatforms() {
		int rand = (int)(Math.random()*10);
		if(rand<3 && platformDue == 0){
			SallyJump.platforms.add(new Platform(100*(rand+1), -50));
			platformDue = 5;
		}
		else{
			if(platformDue > 0)
			platformDue--;
		}
	}
	private void dropPlatforms(int drop) {
		ArrayList<Platform> toRemove = new ArrayList<Platform>();
		for(Platform p: SallyJump.platforms){
			p.drop(-drop);
			if(p.boundingBox().x > 400){
				toRemove.add(p);
			}
		}
		SallyJump.platforms.removeAll(toRemove);
	}

	
	private boolean onPlatform() {
		Rectangle boundingBox = new Rectangle(x+20,y,40,100);
		Platform toRemove = null;
		for(Platform p: SallyJump.platforms ){
			if(p.playerIsOnTopOf(boundingBox)){
				toRemove = p;
			}
		}
		if(toRemove != null){
			if(toRemove.isDisappearing()){
			SallyJump.platforms.remove(toRemove);
			}
			return true;
		}
		return false;
	}
	public void moveLeft() {
		if(speedLeftRight > -20){
		speedLeftRight-=2; 
		}
	}
	public void moveRight() {
		if(speedLeftRight < 20){
			speedLeftRight+=2;
		}
	}
	public void draw(Graphics gffx) {
		gffx.setFont(gffx.getFont().deriveFont(24f));
		gffx.setColor(Color.black);
		gffx.drawString("Score: "+score, 10, 20);
		gffx.drawImage(sally, x,y,null);
		gffx.setColor(Color.black);
		gffx.fillRect(100,20,214,50);
		gffx.setColor(Color.lightGray);
		gffx.fillRect(102,22,210,46);
		gffx.setColor(Color.black);
		gffx.fillRect(105,25,204,40);
		gffx.setColor(Color.darkGray);
		gffx.fillRect(107,27,200,36);
		gffx.setColor(Color.blue);
		gffx.fillRect(107,27,energy,36);
		gffx.setColor(Color.black);
		gffx.drawString("Energy", 150, 50);
	}
	public void flap() {
		if(energy >= 80){
		flapping = 20;
			speedUp = -20;
		}
	}

	
}
	

