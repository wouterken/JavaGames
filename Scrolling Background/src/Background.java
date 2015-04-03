import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Background {
	private ArrayList<Polygon> Ceilings = new ArrayList<Polygon>();
	private ArrayList<Polygon> Floors = new ArrayList<Polygon>();
	private int currentX;
	private int lastCeilingHeight = 300;
	private int lastFloorHeight = 500;
	private int farthestX;
	
	public Background(){
		generateBackground(0);
		cleanBackground();
	}
	private void cleanBackground() {
		ArrayList<Polygon> toRemove = new ArrayList<Polygon>();
		for(Polygon p: Ceilings){
			Rectangle bnds = p.getBounds();
			if(bnds.x + bnds.getWidth()<0){
				toRemove.add(p);
			}
		}
		Ceilings.removeAll(toRemove);
		toRemove.clear();
		for(Polygon p: Floors){
			Rectangle bnds = p.getBounds();
			if(bnds.x + bnds.getWidth()<0){
				toRemove.add(p);
			}
		}
		Floors.removeAll(toRemove);
	}
	private void generateBackground(int x) {
		int totalWidth = x;
		int blockWidth = x;
		int blockHeight = 0;
		
		while(totalWidth<1600+x){
		 blockWidth = (int)(Math.random()*300);
		if(totalWidth + blockWidth > 1600+x){
			blockWidth = 1600+x - totalWidth;
		}
		 blockHeight = (int)(Math.random() * 330);
		 int[] xs = {totalWidth,totalWidth+blockWidth,totalWidth+blockWidth,totalWidth};
		 int[] ys = {lastFloorHeight,800-blockHeight,800,800};
		 Polygon rock = new Polygon(xs,ys,4);
		 Floors.add(rock);
		 lastFloorHeight = 800- blockHeight;
		 totalWidth += blockWidth;
		}
		farthestX = totalWidth;
		 totalWidth = x;
		 blockWidth = x;
		 blockHeight = 0;
		
		while(totalWidth<1600+x){
			 blockWidth = (int)(Math.random()*300);
			if(totalWidth + blockWidth > 1600+x){
				blockWidth  = 1600+x - totalWidth;
			}
			 blockHeight = (int)(Math.random() * 330);
			 int[] xs = {totalWidth,totalWidth+blockWidth,totalWidth+blockWidth,totalWidth};
			 int[] ys = {lastCeilingHeight,blockHeight,0,0};
			 Polygon rock = new Polygon(xs,ys,4);
			 Ceilings.add(rock);
			 lastCeilingHeight = blockHeight;
			 totalWidth += blockWidth;
			}
	}
	public void draw(Graphics gffxs) {
		gffxs.setColor(Color.black);
		gffxs.fillRect(0,0,800,800);
		gffxs.setColor(Color.white);
		for(Polygon p:Ceilings){
			gffxs.fillPolygon(p);
		}
		for(Polygon p:Floors){
			gffxs.fillPolygon(p);
		}
	}
	public boolean isTouching(Rectangle boundingBox) {
		for(Polygon p: Ceilings){
			if(p.intersects(boundingBox)){
				return true;
			}
		}
		for(Polygon p: Floors){
			if(p.intersects(boundingBox)){
				return true;
			}
		}
		return false;
	}
	public void step(int i) {
		if(i<0) i = 0;
		for(Polygon p: Ceilings){
			p.translate(-6 -i, 0);
		}
		for(Polygon p: Floors){
			p.translate(-6 -i, 0);
		}
		farthestX -= (6+i);
		currentX += (6+i);
		if(currentX> 750){
			currentX = 0;
			cleanBackground();
			generateBackground(800);
		}
	}

}
