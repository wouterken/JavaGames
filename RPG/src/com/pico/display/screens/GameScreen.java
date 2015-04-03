package com.pico.display.screens;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.pico.controllers.PlayerKeyController;
import com.pico.logic.locations.Area;
import com.pico.objects.characters.player.Player;
import com.pico.prefs.PREFS;

public class GameScreen  extends UIComponent{

	private BufferedImage image;
	private BufferedImage overlay;
	private Graphics2D overlayGffx;
	private Graphics2D gffx;
	private Area world;
	private BufferedImage worldImage;
	private Player player;
	private Message message;
	private long timeNow;
	private long timeSinceLast;
	private long timeLast;
	private BufferedImage background;
	private ArrayList<KeyListener> keyListeners = new ArrayList<KeyListener>();
	
	public GameScreen(){
		image = new BufferedImage(PREFS.GAMEWIDTH, PREFS.GAMEHEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
		gffx = image.createGraphics();
		overlay = new BufferedImage(PREFS.GAMEWIDTH, PREFS.GAMEHEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
		overlayGffx = overlay.createGraphics();
		overlayGffx.setComposite(AlphaComposite.SrcOver);
		overlayGffx.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		PREFS.initGffx(gffx);
		PREFS.initGffx(overlayGffx);
		keyListeners.add(new PlayerKeyController(player.getInstance()));
	}
	
	public void draw(Graphics _gffx, int width, int height) {
		if(gffx == null || overlayGffx == null){
			return;
		}
	
		overlayGffx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.0f));  
		overlayGffx.clearRect(0, 0, overlay.getWidth(), overlay.getHeight());
		overlayGffx.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));  
	
		world.draw(gffx,overlayGffx);
		if(PREFS.DIALOGACTIVE){
			message.drawMessage(gffx, false);
		}
		_gffx.drawImage(image, 0, 0, width,height, 0, 0, image.getWidth(), image.getHeight(), null);
		_gffx.drawImage(overlay, 0, 0, width, height, 0, 0, overlay.getWidth(), overlay.getHeight(), null);
		_gffx.drawString(""+PREFS.CURFPS, 100, 100);
		
	}

	public void setWorld(Area _world) {
		world = _world;
	}

	public void setPlayer(Player _player) {
		player = _player;
	
	}


	public ArrayList<KeyListener> getKeyListeners() {
		
		return keyListeners ;
	}
}
