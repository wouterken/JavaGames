package gamelogic.weapons;

import gamelogic.Block;
import gamelogic.World;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import prefs.PRFS;

public class Projectile {

	private double y;
	private double x;
	private int height;
	private int width;
	private boolean right;
	private double angle;
	public BulletType type;
	private double weight;
	public boolean switchedLeftRight;
	public boolean switchedUpDown;
	private double lastY;
	private double lastX;
	private Rectangle toHit;
	public boolean bounce;
	public int bounceCount = -1;

	public Projectile(double angle, int x, int y, boolean facingLeft, boolean bouncy){
		this.angle = angle-90;
		this.y = y+20;
		weight = 0.5;
		this.width = 5;
		this.height = 5;
		this.right = facingLeft;
		this.x = x;
		double offsetY = 0;
		if(!right){
			moveBullet(right,3);
			offsetY = (Math.abs(this.angle)/2)*((this.angle>0)?1:-1);
		}
		else{
			offsetY = (Math.abs(this.angle)/2)*((this.angle>0)?-1:1);
			moveBullet(right,9);
		}
		if(bouncy) type = BulletType.BOUNCYBULLET;
		else type = BulletType.LIGHTBULLET;
		this.y+= offsetY;
		for (Block b: World.visibleBlocks){
			if(this.collidingWith(b.getBoundingBox())){
				World.projectiles.remove(this);
			}
		}
	}

	public boolean draw(Graphics paintBrush, Point position) {
		boolean delete;
		paintBrush.setColor(Color.red);
		paintBrush.fillOval((int)x-(position.x-300)-2,(int)y-(position.y-300)-2,width+4,height+4);
		paintBrush.setColor(Color.orange);
		paintBrush.fillOval((int)x-(position.x-300),(int)y-(position.y-300),width,height);
		moveBullet(right,1);
		
		do{
			delete = true;
			if(x-(position.x-300) > PRFS.GameWidth*2){
				break;
			}
			if(x-(position.x-300)+width < -PRFS.GameWidth){
				break;
			}
			if(y-(position.y-300) > 2*PRFS.GameHeight){
				break;
			}
			if(y-(position.y-300)+height < -PRFS.GameHeight){
				break;
			}
			delete = false;
		}while(false);
		return delete;
	}

	private void moveBullet(boolean left, int i) {
		Rectangle boundingBox = new Rectangle((int)x-10,(int)y-10,width+20,height+20);
		for(Block c: World.visibleBlocks){
			if(c.getBoundingBox().contains(boundingBox) && c.isSolid()){World.projectiles.remove(this);
			return;
			}
		}
		while(i > 0){
		int xMult = ((left)?1:-1);
		int yMult = ((angle>0)?1:-1);
		double angleInRad = Math.toRadians(Math.abs(angle));
		double yOffset = Math.sin(angleInRad)*10*yMult;
		double xOffset = Math.cos(angleInRad)*10*xMult;
		lastX = x;
		lastY = y;
		x += xOffset;
		y += yOffset;
		i--;
		}
	}
	public boolean collidingWith(Rectangle objectBoundingBox){
		Rectangle boundingBox = new Rectangle((int)x,(int)y, width, height);
		if(objectBoundingBox.intersects(boundingBox) || objectBoundingBox.contains(boundingBox))
		return true;
		else return false;
	}

	public Point getImpactPoint() {
		int xMult = ((right)?1:-1);
		int yMult = ((angle>0)?1:-1);
		double angleInRad = Math.toRadians(Math.abs(angle));
		int yOffset = (int)(Math.sin(angleInRad)*10*yMult);
		int xOffset = (int)(Math.cos(angleInRad)*10*xMult);
		return new Point(xOffset,yOffset);
	}

	public double getWeight() {
		return weight;
	}
	public void clearToHit(){
		toHit = null;
	}
	public void addToHitList(Rectangle boundingBox){
		if(toHit == null){
			toHit = boundingBox;
		}else{
			if(boundingBox.x < toHit.x){
				toHit.x = boundingBox.x;
			}
			if(boundingBox.x+boundingBox.width < toHit.x + toHit.width){
				toHit.width = boundingBox.x + boundingBox.width - toHit.x;
			}
			if(boundingBox.y < toHit.y){
				toHit.y = boundingBox.y;
			}
			if(boundingBox.y+boundingBox.height < toHit.y + toHit.height){
				toHit.height = boundingBox.y + boundingBox.height - toHit.y;
			}
		}
	}
	public void bounce() {
		if(!bounce || toHit == null) return;
		Rectangle boundingBox = toHit.getBounds();
		if(x+width >= boundingBox.x  && lastX< boundingBox.x){
			right = !right;
		}
		if (x <= boundingBox.x+boundingBox.width && lastX > boundingBox.x+boundingBox.width){
			right = !right;
		}

		if(y+height >= boundingBox.y && lastY < boundingBox.y){
			angle *= -1;
		}
		if(y <= boundingBox.y+boundingBox.height &&  lastY > boundingBox.y+boundingBox.height){
			angle *= -1;
		}

		
	}
}
