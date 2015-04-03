package com.pico.logic.locations;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.pico.display.Controller;
import com.pico.display.screens.Message;
import com.pico.events.EventBus;
import com.pico.events.EventListener;
import com.pico.events.GameEvent;
import com.pico.events.LeftEvent;
import com.pico.events.RightEvent;
import com.pico.input.ImageLoader;
import com.pico.logic.KeyComparator;
import com.pico.objects.DrawableObject;
import com.pico.objects.OverlayObject;
import com.pico.objects.WorldObject;
import com.pico.objects.characters.player.DIR;
import com.pico.objects.characters.player.Player;
import com.pico.prefs.PREFS;
import com.pico.prefs.SHADOWS;

public class Area implements EventListener {
	
	//private static BufferedImage renderedMask;
	private static BufferedImage areaImage;
	private static BufferedImage background;
	public int black = -16777216;
	public int offsetX = 0;
	public int offsetY = 0;
	public int prevX = -1;
	public int prevY = -1;
	public ConcurrentHashMap<Integer, DrawableObject> objects = new ConcurrentHashMap<Integer, DrawableObject>();
	private boolean walkOnly;
	private String name;
	private boolean staticBackground;

	private ArrayList<BufferedImage> overlayImages;
	private int currentMaskIdx;
	private ArrayList<BufferedImage> areaMasks;
	private ArrayList<BufferedImage> renderedMasks = new ArrayList<BufferedImage>();
	

	public Area(HashMap<String, String> prefSet,
			ArrayList<DrawableObject> objectSet) {
		name = prefSet.get("name");
		background = (BufferedImage) ImageLoader.loadImage(prefSet.get("backgroundImage"));
		areaImage = (BufferedImage) ImageLoader.loadImage(prefSet.get("areaImage"));
		currentMaskIdx = Integer.parseInt(prefSet.get("initialLevel"));
		
		areaMasks =  ImageLoader.loadImages(prefSet.get("areaMasks"));
		
		overlayImages =  ImageLoader.loadImages(prefSet.get("overlays"));
		int overlayLevel = 1;
		for (BufferedImage overlayImage : overlayImages) {
			addObject(new OverlayObject(overlayImage, overlayLevel++),false);
		}
		staticBackground = Boolean.parseBoolean(prefSet.get("staticBackground"));
		for(BufferedImage areamask: areaMasks){
			BufferedImage renderedMask = new BufferedImage(areamask.getWidth(),areamask.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
			renderedMasks.add(renderedMask);
		}
		for(DrawableObject ob: objectSet){
			addObject(ob,false);
		}
		reconstructWorldMasks();
		EventBus.registerListener(new LeftEvent(), this);
	}


	private void addObject(DrawableObject worldObject,boolean reconstruct) {
		String key = worldObject.overlayLevel()+""+String.format("%04d%04d",worldObject.getY(),worldObject.getX());
		int index = Integer.parseInt(key);
		
		objects.put(index, worldObject);
		if(reconstruct) reconstructWorldMasks();
	}
	private void reconstructWorldMasks() {
		if(renderedMasks == null) return;
		for(BufferedImage renderedMask: renderedMasks){
			int level = renderedMasks.indexOf(renderedMask);
			Graphics2D gffx = renderedMask.createGraphics();
			gffx.clearRect(0,0,renderedMask.getWidth(),renderedMask.getHeight());
			gffx.drawImage(areaMasks.get(level), 0,0,null);
			BufferedImage mask = null;
			for(DrawableObject ob: objects.values()){
				if(ob.overlayLevel() != level){
					continue;
				}
				if(ob instanceof Player){
					continue;
				}
				mask = (BufferedImage) ob.getMask();
				if(mask != null){
					gffx.setColor(new Color(black));
					System.out.println(ob.getX()+" : "+ob.getY()+" : "+mask.getWidth()+" : "+mask.getHeight());
					gffx.drawImage(mask,ob.getX(), ob.getY(), mask.getWidth(), mask.getHeight(),null);
				}
			}
		}
	}


	private void moveObject(){
		
	}
	private void removeObject(){
		
	}

	public void moveRight(Player player){
		int stepSize = ((walkOnly)?PREFS.DEFAULTSTEPSIZE:PREFS.STEPSIZE);
		if(player.x + player.width >= PREFS.GAMEWIDTH - PREFS.NOMOVEBORDERSIZE && offsetX + player.x + player.width + stepSize < areaImage.getWidth() -PREFS.NOMOVEBORDERSIZE ){
			offsetX += stepSize;
		}else{
		player.x += stepSize;
		}
	}
	public void moveLeft(Player player){
		int stepSize = ((walkOnly)?PREFS.DEFAULTSTEPSIZE:PREFS.STEPSIZE);
		
		if(player.x <= PREFS.NOMOVEBORDERSIZE && offsetX > 0){
			offsetX -= stepSize;
		}else{
			player.x -= stepSize;
		}
		
	}
	public void moveUp(Player player){
		int stepSize = ((walkOnly)?PREFS.DEFAULTSTEPSIZE:PREFS.STEPSIZE);
		
		if(player.y <= PREFS.NOMOVEBORDERSIZE && offsetY > 0){
			offsetY -= stepSize;
		}else{
			player.y -= PREFS.STEPSIZE;
		}
		
	}
	public void moveDown(Player player){
		int stepSize = ((walkOnly)?PREFS.DEFAULTSTEPSIZE:PREFS.STEPSIZE);

		if(player.y + player.height >= PREFS.GAMEHEIGHT - PREFS.NOMOVEBORDERSIZE && offsetY + player.y + player.height + stepSize < areaImage.getHeight() -PREFS.NOMOVEBORDERSIZE ){
			offsetY += stepSize;
		}else{
			player.y += stepSize;
		}
		
	}
	
	
	public boolean canMoveRight(Rectangle boundingBox){
		walkOnly = false;
		int x = boundingBox.x + boundingBox.width;
		int y = boundingBox.y;
		int maskIdx = currentMaskIdx;
		try{
			for(int i = 1;i<=PREFS.STEPSIZE; i++){
			for(int j = PREFS.DEFAULTSTEPSIZE; j<=boundingBox.height - PREFS.DEFAULTSTEPSIZE; j++){
				if(renderedMasks.get(maskIdx).getRGB(x+i+offsetX, y+j+offsetY) == black){
					if(i >= PREFS.DEFAULTSTEPSIZE){
						walkOnly = true;
						return true;
						}
					return false;
				}
			}
		}
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	public boolean canMoveLeft(Rectangle boundingBox){
		walkOnly = false;
		int x = boundingBox.x;
		int y = boundingBox.y;
		int maskIdx = currentMaskIdx;
		try{
			for(int i = 1;i<=PREFS.STEPSIZE; i++){
				for(int j = PREFS.DEFAULTSTEPSIZE; j<=boundingBox.height - PREFS.DEFAULTSTEPSIZE; j++){
				if(renderedMasks.get(maskIdx).getRGB(x-i+offsetX, y+j+offsetY) == black){
					if(i >= PREFS.DEFAULTSTEPSIZE){
						walkOnly = true;
						return true;
						}
					return false;
				}
			}
		}
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	public boolean canMoveDown(Rectangle boundingBox){
		walkOnly = false;
		int x = boundingBox.x;
		int y = boundingBox.y + boundingBox.height;
		int maskIdx = currentMaskIdx;
		try{
		for(int i = 1;i<=PREFS.STEPSIZE; i++){
			for(int j = PREFS.DEFAULTSTEPSIZE;j<= boundingBox.width - PREFS.DEFAULTSTEPSIZE; j++){
				if(renderedMasks.get(maskIdx).getRGB( x+j+offsetX,y+i+offsetY) == black){
					if(i >= PREFS.DEFAULTSTEPSIZE){
						walkOnly = true;
						return true;
						}
					return false;
				}
			}
		}
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	public boolean canMoveUp(Rectangle boundingBox){
		walkOnly = false;
		int maskIdx = currentMaskIdx;
		int x = boundingBox.x;
		int y = boundingBox.y;
		try{
			for(int i = 1;i<=PREFS.STEPSIZE; i++){
				for(int j = PREFS.DEFAULTSTEPSIZE;j<= boundingBox.width - PREFS.DEFAULTSTEPSIZE; j++){
				if(renderedMasks.get(maskIdx).getRGB(x+j+offsetX,y-i+offsetY) == black){
					if(i >= PREFS.DEFAULTSTEPSIZE){
						walkOnly = true;
						return true;
						}
					return false;
				}
			}
		}
		}catch (Exception e) {
			return false;
		}
		return true;
	}



	public void draw(Graphics2D gffx, Graphics2D overlayGffx) {
		refreshPlayerPosition();
		drawBackground(gffx);
		drawPlayerReflection(gffx);
		drawWorld(gffx);
		drawObjects(overlayGffx);
		//render(gffx,renderedMask,0,0);
//		drawShadows(gffx);
		//drawOverlay(overlayGffx);
	}
	private void drawPlayerReflection(Graphics2D gffx){
		if(!PREFS.REFLECTIONS) return;
		Player.getInstance().drawReflection(gffx);
	}
	private void drawBackground(Graphics2D gffx){
		if(staticBackground){
			render(gffx,background,offsetX,offsetY,false);
		}else
		render(gffx,background,0,0,true);
	}
	private void drawWorld(Graphics2D gffx){
		render(gffx,areaImage,0,0,true);
	}
//	private void drawOverlay(Graphics2D gffx,int idx){
//		render(gffx,overlays.get(idx),0,0,true);
//	}
	private void refreshPlayerPosition() {
		Player player = Player.getInstance();
		objects.remove(player.playerIdx);
		int index = Integer.parseInt(player.overlayLevel()+""+String.format("%04d%04d",player.y+offsetY,player.x+offsetX));
		
		if(!objects.containsKey(index)){
			objects.put(index, player);
			player.playerIdx = index;
		}
	}

	public void render(Graphics2D gffx,Image image,int x, int y){
		do{
			x = x - offsetX;
			y = y - offsetY;
			if(x > PREFS.GAMEWIDTH )
				break;
			if(y > PREFS.GAMEHEIGHT )
				break;
			if(x + image.getWidth(null) < 0 )
				break;
			if(y + image.getHeight(null) < 0 )
				break;
			
			gffx.drawImage(image,x,y,null);
		}while(false);
		
	}
	public void render(Graphics2D gffx,Image image,int x, int y,boolean isStatic){
		if(isStatic){
			gffx.drawImage(image,0,0,PREFS.GAMEWIDTH,PREFS.GAMEHEIGHT,x+offsetX,y+offsetY,x+offsetX+PREFS.GAMEWIDTH,y+offsetY+PREFS.GAMEHEIGHT,null);
		}else{
			gffx.drawImage(image,0,0,PREFS.GAMEWIDTH,PREFS.GAMEHEIGHT,0,0,PREFS.GAMEWIDTH,PREFS.GAMEHEIGHT,null);
			
		}
	}

	public void renderShadow(Graphics2D gffx,int x,int bottom, int width){
		int shadowHeight = (int) (width * SHADOWS.MULT/2);
		gffx.drawImage(SHADOWS.IMAGE,x-offsetX,bottom-shadowHeight-offsetY,x+width-offsetX,bottom+shadowHeight-offsetY,0,0,SHADOWS.WIDTH,SHADOWS.HEIGHT,null);
	}
	public void drawObjects(Graphics2D gffx){
		
		List<Integer> keys = Arrays.asList(objects.keySet().toArray(new Integer []{}));
		
		Collections.sort(keys, new KeyComparator());
		DrawableObject curOb;
		for(int key: keys){
			curOb = objects.get(key);
			
			if(curOb instanceof Player){
				((Player) curOb).draw(gffx);
				continue;
			}
			if(SHADOWS.ON && curOb.hasShadow()){
				renderShadow(gffx,curOb.getX(),curOb.getBottom(),curOb.getWidth());
			}			
			render(gffx,curOb.getImage(),curOb.getX(),curOb.getImageY());
		
		}
	}


	public int getLevel() {
		return currentMaskIdx;
	}

	public boolean switchDownLevel(int level) {
		try{
			areaMasks.get(level-1);
			currentMaskIdx = level-1;
			return true;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	public boolean switchUpLevel(int level) {
		try{
			areaMasks.get(level+1);
			currentMaskIdx = level+1;
			return true;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}


	public void setLevel(int level) {
		currentMaskIdx = level;
		
	}


	public void fire(GameEvent event) {
		System.out.println("im listening too!");
	}


	public void activate(Rectangle boundingBox, DIR direction) {
		DrawableObject object = getTouching(boundingBox,direction);
		if(object == null){
			Controller.setScreen(new Message(new String[]{"Nothing"}));
		}else{
			object.activate();
		}
	}
	private DrawableObject getTouching(Rectangle boundingBox, DIR direction) {
		try{
		int px = boundingBox.x;
		int py = boundingBox.y;
		int bottomX = 1;
		int bottomY = 1;
		int topX = PREFS.STEPSIZE;
		int topY = PREFS.STEPSIZE;
		int xMult = 1;
		int yMult = 1;
		int objx = 0;
		int objy = 0;
		int objw = 0;
		int objh = 0;
		Rectangle objBoundingbox;
		switch(direction){
			case UP:
				
				topX = boundingBox.width - PREFS.DEFAULTSTEPSIZE;
				bottomX = PREFS.DEFAULTSTEPSIZE;
				yMult = -1;
			break;
			case DOWN:
				topX = boundingBox.width - PREFS.DEFAULTSTEPSIZE;
				bottomX = PREFS.DEFAULTSTEPSIZE;
				py += boundingBox.height;
			break;
			case LEFT:
				bottomY = PREFS.DEFAULTSTEPSIZE;
				topY = boundingBox.height - PREFS.DEFAULTSTEPSIZE;
				xMult = -1;
			break;
			case RIGHT:
				bottomY = PREFS.DEFAULTSTEPSIZE;
				topY = boundingBox.height - PREFS.DEFAULTSTEPSIZE;
				px += boundingBox.width;
			break;
			
				
		}
		int ox = 0;
		int oy = 0;
		for(int x = bottomX; x< topX; x+=3){
			for(int y = bottomY; y< topY; y+=3){
				for(DrawableObject object: objects.values()){
					objBoundingbox = new Rectangle(object.getX(), object.getY(), object.getWidth(), object.getHeight());

					ox = (x * xMult) + px + offsetX;
					oy = (y * yMult) + py + offsetY;
				
					if(objBoundingbox.contains(ox, oy)){
						return object;
					}
				}
			}
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	




	
}
