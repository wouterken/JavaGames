package gamelogic.entities;

import gamelogic.World;
import gamelogic.GameKeyController.direction;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import display.ImageHandler;

public class Enemy extends Entity{

	private int width;
	private int height;
	private BufferedImage enemyImage;
	private Queue<direction> directions = new LinkedList<direction>();
	
	public Enemy(int x, int y){
		super(x,y,100,100,ImageHandler.split(3, "walk.png"),false);
		this.weight = 10;
	}
	@Override
	public void refresh(World world) {
		ai(world.currentPlayer);
		super.refresh(world);
	}
	private void ai(Player player) {
		Point playerPosition = player.getPosition();
		if(directions.size() < 10){
		if(position.x < playerPosition.x && ((int)Math.random()*100)<2)
			directions.add(direction.RIGHT);
		if(position.x > playerPosition.x && ((int)Math.random()*100)<2) 
			directions.add(direction.LEFT);
		if(position.y+height > playerPosition.y +player.height && (position.x > playerPosition.x - 100 && position.x < playerPosition.x + 100)&& ((int)Math.random()*100)<2) 
			directions.add(direction.UP);
		
		}
		else{directions.clear();
		return;}
		if(!directions.isEmpty()){
		move(directions.poll());
		}else{
		}

	}
}
