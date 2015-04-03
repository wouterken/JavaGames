package gamelogic;

import gamelogic.entities.Entity;
import gamelogic.entities.Player;
import gamelogic.weapons.Projectile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import display.ImageHandler;

import prefs.PRFS;

public class Block {
	public static int blocksDrawn;
	private int height;
	private int width;
	public final int firstX;
	public final int firstY;
	private int x;
	private int y;
	private Rectangle boundingBox;
	private boolean solid;
	private BlockType blockType;
	public static Image platform = ImageHandler.loadImage("platform.png");
	public static Image block = ImageHandler.loadImage("block.png");
	public Block(BlockType b, int x, int y, int width, int height,boolean solid){
		firstX = x;
		firstY = y;
		this.blockType = b;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.boundingBox = new Rectangle(x,y,width,height);
		this.solid = solid;
	}
	public void draw(Graphics gffx,int offsetX,int offsetY){
		do{
		if(this.x-offsetX > PRFS.GameWidth){
			break;
		}
		if(this.x-offsetX+width < 0){
			break;
		}
		if(this.y-offsetY > PRFS.GameHeight){
			break;
		}
		if(this.y-offsetY+height < 0){
			break;
		}
		drawImage(gffx,offsetX,offsetY);
		blocksDrawn++;
		return;
		}while(false);
		//System.out.println("Block out of screen");
	}	
	private void drawImage(Graphics gffx, int offsetX, int offsetY) {
		switch(blockType){
		case PLATFORM:
			gffx.drawImage(platform,x-offsetX,y-offsetY,width,height,null);
			break;
		case BLOCK:
			gffx.drawImage(block,x-offsetX,y-offsetY,width,height,null);
			break;
		}
	}
	public Rectangle getBoundingBox() {
		return boundingBox;
	}
	@Override
	public String toString() {
		return("X : "+x+", Y : "+y);
	}
	public String checkBounds(Entity entity){
		int top, bottom, left, right, boxTop, boxBottom, boxLeft, boxRight;
		top = entity.getPosition().y;
		bottom = entity.getPosition().y+entity.getHeight();
		left = entity.getPosition().x;
		right = entity.getPosition().x+entity.getWidth();
		boxTop = boundingBox.y;
		boxBottom = boundingBox.y+boundingBox.height;
		boxLeft = boundingBox.x;
		boxRight = boundingBox.x + boundingBox.width;
		double speed = entity.getDownSpeed();
		double groundSpeed = entity.getGroundSpeed();
	do{
		if(right<=boxLeft){
			//System.out.println("Break Left");
			break;
		}
		if(left>=boxRight){
			//System.out.println("Break Right");
			break;
		}
		if(bottom > boxTop){
			//System.out.println("Break Too Low");
			break;
		}
		if(bottom+speed < boxTop){
			//System.out.println("Break Too High");
			break;
		}
		if(bottom+speed >= boxTop ){
			//System.out.println("not too high");
			entity.moveDown(boundingBox.y - entity.getHeight());
			return "ontop";
			}

	}while(false);
	if(!solid) return "";
	do{
		if(right<=boxLeft){
			//System.out.println("Break Left");
			break;
		}
		if(left>=boxRight){
			//System.out.println("Break Right");
			break;
		}
		if(top < boxBottom){
			//System.out.println("Break Too Low");
			break;
		}
		if(top+speed > boxBottom){
			//System.out.println("Break Too High");
			break;
		}
		if(top+speed <= boxBottom ){
			//System.out.println("not too high");
			entity.moveDown(boxBottom);
			return "beneath";
			}

	}while(false);
	do{
		if(bottom<=boxTop){
			//System.out.println("Break Left");
			break;
		}
		if(top>=boxBottom){
			//System.out.println("Break Right");
			break;
		}
		if(right > boxLeft){
			//System.out.println("Break Too Low");
			break;
		}
		if(right+groundSpeed < boxLeft){
			//System.out.println("Break Too High");
			break;
		}
		if(right+groundSpeed >= boxLeft ){
			//System.out.println("not too high");
			entity.moveLeft(boxLeft - entity.getWidth());
			return "left";
			}

	}while(false);
	do{
		if(bottom<=boxTop){
			//System.out.println("Break Left");
			break;
		}
		if(top>=boxBottom){
			//System.out.println("Break Right");
			break;
		}
		if(left < boxRight){
			//System.out.println("Break Too Low");
			break;
		}
		if(left+groundSpeed > boxRight){
			//System.out.println("Break Too High");
			break;
		}
		if(left+groundSpeed <= boxRight ){
			//System.out.println("not too high");
			entity.moveLeft(boxRight);
			return "right";
			}

	}while(false);
		return "";
	}
	public boolean isSolid() {
		return solid;
	}
	public boolean isVisible(int offsetX, int offsetY) {
		do{
			if(this.x-offsetX > PRFS.GameWidth){
				break;
			}
			if(this.x-offsetX+width < 0){
				break;
			}
			if(this.y-offsetY > PRFS.GameHeight){
				break;
			}
			if(this.y-offsetY+height < 0){
				break;
			}
			return true;
			}while(false);
		return false;
	}
	public boolean hit(Projectile p) {
		
		switch(blockType){
		case PLATFORM:
			return false;
		case BLOCK:
			switch(p.type){
			case LIGHTBULLET:
				return true;
			case BOUNCYBULLET:
				if(p.bounceCount == -1){
					p.bounceCount = 3;
				}else if(p.bounceCount == 0){
					return true;
				}else{
				p.bounceCount--;
				}
				p.bounce = true;
				p.addToHitList(getBoundingBox());
				return false;
			}
			
		}
		return false;
	}
		

}
