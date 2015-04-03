package gamelogic.entities;

import gamelogic.Block;
import gamelogic.World;
import gamelogic.GameKeyController.direction;
import gamelogic.weapons.Projectile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import display.ImageHandler;

import prefs.PRFS;

public class Entity{

	protected int width;
	protected int height;
	protected BufferedImage entityImage;
	protected Point position;
	protected double speed=0;
	protected double gravity=0.3;
	protected double groundSpeed;
	protected boolean canJump;
	protected boolean dropDown;
	protected boolean facingLeft;
	protected boolean falling;
	protected double angle = 90;
	protected int weight;
	private double maxGroundSpeed;
	
	protected int imageIndex = 1;
	protected int walkIndex = 1;
	protected int walkMult = 1;
	protected Image[] walkImages;
	protected boolean bouncy;
	protected double entitySpeed = 0.25;
	
	public Entity(int x, int y,int width, int height, Image[] walkImages, boolean bouncy){

		position = new Point(x,y);
		this.width = width;
		this.height = height;
		this.walkImages = walkImages;
		this.bouncy = bouncy;
		if(walkImages == null){
			this.walkImages = new Image[3];
			for(int i = 0; i<3; i++){
			BufferedImage entImage = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
			Graphics entGffx = entImage.createGraphics();
			entGffx.setColor(Color.black);
			entGffx.fillOval(0, 0, width, height);
			this.walkImages[i] = entImage;
			}
		}
		
	}
	public Point getPosition() {
		return position;
	}
	public double getDownSpeed(){
		return speed;
	}
	public double getGroundSpeed(){
		return groundSpeed;
	}
	public void setPosition(Point position) {
		this.position = position;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void moveDown(int y) {
		position.move(position.x, y);
	}
	public void moveLeft(int x) {
		position.move(x, position.y);
	}
	public void move(direction dir) {
		switch(dir){
		case UP:
			jump(0);
			break;
		case DOWN:
			crouch();
			break;
		case LEFT:
			moveLeft();
			break;
		case RIGHT:
			moveRight();
			break;
		default:
		}

		}

	private void jump(int i) {
		if(canJump){
		if (i == 0){
		speed = -10;
		canJump = false;
		}else{
			speed = -i;
			canJump = false;
		}
	}
	}

	private void crouch() {
		dropDown = true;
	}

	private void moveLeft() {
		if(groundSpeed > ((this instanceof Player)?-4:-1)){
			this.groundSpeed-= entitySpeed;
		}
	}

	private void moveRight() {
		if(groundSpeed < ((this instanceof Player)?4:1)){
			groundSpeed+= entitySpeed ;
		}
	}
	public void refresh(World world) {
		
		speed += gravity;
		normalizeGroundSpeed();
		falling = true;
		boolean stationairy = false;
//		for(Entity e: world.getEntities(this)){
//			String status = e.checkBounds(this);
//			if(status.equals("ontop")){
//				if(bouncy && speed > 0){
//					speed = -Math.abs(0.8*speed);
//				}else{
//				speed = 0;
//				falling = false;
//				canJump = true;
//				}
//			}
//			if(status.equals("beneath")){
//				falling = true;
//				if(speed < 0) speed *= -1;
//			}
//			if(status.equals("left") || status.equals("right")){
//				
//				if(bouncy){
//					groundSpeed = -0.8*groundSpeed;
//					if(groundSpeed< 0.5 && groundSpeed > -0.5) stationairy = true;
//				}
//				else{
//					stationairy = true;
//				}
//			}
//			
//		}
		for(Block b: world.getBlocks()){
			String status = b.checkBounds(this);
			if(status.equals("ontop")){
					if(bouncy && speed > 0){
						speed = -0.8*speed;
					}else{
					speed = 0;
					falling = false;
					canJump = true;
					}
				if((b.isSolid()) && dropDown){
					dropDown = false;
				}
			}
			if(status.equals("beneath")){
				falling = true;
				if(speed < 0) speed *= -1;
			}
			if(status.equals("left") || status.equals("right")){
				
				if(bouncy){
					groundSpeed = -0.8*groundSpeed;
					if(groundSpeed< 0.5 && groundSpeed > -0.5) stationairy = true;
				}
				else{
					stationairy = true;
				}
			}
			
			
		}

		if(dropDown){
			position.translate(0,1);
		}
		dropDown = false;
		if(!falling){
			speed = 0;
		}
		else{
			position.translate(0,(int) speed);
		}
		if(!stationairy){
		position.translate((int)groundSpeed, 0);
		}
		if(groundSpeed > -0.2 && groundSpeed < 0.2){
			groundSpeed = 0;
		}else{
			slowDownLeftRightMovement();
		
		}
	}
	private void normalizeGroundSpeed() {
		if(groundSpeed > 4) groundSpeed = 4;
		if(groundSpeed <-4) groundSpeed = -4;
	}
	public void slowDownLeftRightMovement() {
		if(groundSpeed >0){
			facingLeft = true;
			groundSpeed -= 0.1;
		}
		else{
			facingLeft = false;
			groundSpeed += 0.1;
		}
		
	}
	public void draw(Graphics gffx, int offsetX, int offsetY) {
		if(PRFS.frmNum == 0)checkWalkIndex();
		if(facingLeft){
			gffx.drawImage(walkImages[walkIndex], position.x-offsetX, position.y-offsetY,width,height,null);
		}else{
			Image img = walkImages[walkIndex];
			gffx.drawImage(img,position.x-offsetX,position.y-offsetY,position.x+width-offsetX,position.y+height-offsetY ,img.getWidth(null), 0,0,img.getHeight(null),null);	
		}
	}
	public void hit(Projectile p) {
		switch(p.type){
		case LIGHTBULLET:
			absorbImpact(p.getImpactPoint(),p.getWeight());
		
		case BOUNCYBULLET:
		absorbImpact(p.getImpactPoint(),p.getWeight());
		p.addToHitList(getBoundingBox());
	}
	}
	private void absorbImpact(Point impactPoint, double weight) {
		if(weight> 20){
			groundSpeed += impactPoint.x*2;
			speed += impactPoint.y;
		}else{
			groundSpeed += (int)((impactPoint.x)*weight)*2;
			speed += (int)((impactPoint.y)*weight);
		}
	}
	public Rectangle getBoundingBox(){
		return new Rectangle(position.x,position.y,width,height);
	}
	@Override
	public String toString() {
		return "X: "+position.x+" Y:"+position.y+" width: "+width+" heigth"+height+" Position:" +position;
	}
	public String checkBounds(Entity entity){
		int top, bottom, left, right, boxTop, boxBottom, boxLeft, boxRight;
		top = entity.getPosition().y;
		bottom = entity.getPosition().y+entity.getHeight();
		left = entity.getPosition().x;
		right = entity.getPosition().x+entity.getWidth();
		boxTop = position.y;
		boxBottom = position.y+height;
		boxLeft = position.x;
		boxRight = position.x + width;
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
			entity.moveDown(position.y - entity.getHeight());
			if(entity.getWeight()> this.weight){
				//this.speed += (entity.getWeight()-this.weight);
			}
			return "ontop";
			}

	}while(false);
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
			if(entity.getWeight()> this.weight){
				this.speed -= (entity.getWeight()-this.weight);
			}
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
			if(entity.getWeight()> this.weight){
				this.groundSpeed += (entity.getWeight()-this.weight)/2;
				normalizeGroundSpeed();
			}
			else
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
			if(entity.getWeight()> this.weight){
				this.groundSpeed -= (entity.getWeight()-this.weight)/2;
				normalizeGroundSpeed();
			}
			else{
			entity.moveLeft(boxRight);
			}
			return "right";
			}

	}while(false);
		return "";
	}
	private int getWeight() {
		return weight;
	}
	public boolean isVisible(int offsetX, int offsetY) {
		do{
			if(this.position.x-offsetX > PRFS.GameWidth){
				break;
			}
			if(this.position.x-offsetX+width < 0){
				break;
			}
			if(this.position.y-offsetY > PRFS.GameHeight){
				break;
			}
			if(this.position.y-offsetY+height < 0){
				break;
			}
			return true;
			}while(false);
		return false;
	}
	protected void checkWalkIndex() {
				if(walkIndex == walkImages.length-1 || walkIndex == 0){
					walkMult *= -1;
				}
				walkIndex+= walkMult;
				
				if(walkIndex > walkImages.length || walkIndex < 0 || groundSpeed >= -0.5 && groundSpeed <= 0.5 || falling){
					walkIndex = ((int)walkImages.length/2);
				}
		}
	
}
