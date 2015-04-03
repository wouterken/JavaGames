package com.pico.prefs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pico.objects.characters.player.DIR;
import com.pico.objects.characters.player.Player;

public class PREFS {
	
	public static HashMap <String,String> prefs = loadPrefsFile();
	
	public static boolean DEBUG = true;
	public static boolean DIALOGACTIVE = false;
	public static int CURFPS;
	
	public static String GAMETITLE		= "My Cool Game";
	public static int GAMEWIDTH 		= Int("gameWidth");
	public static int GAMEHEIGHT 		= Int("gameHeight");
	public static int CANVASWIDTH 		= GAMEWIDTH;
	public static int CANVASHEIGHT 		= GAMEHEIGHT;
	public static int STEPSIZE 			= Int("stepSize");
	public static int DEFAULTSTEPSIZE 	= Int("defaultStepSize");
	public static int TURBOSTEPSIZE 	= Int("turboStepSize");
	public static int NOMOVEBORDERSIZE 	= Int("borderSize");
	public static int MESSAGEPADDINGX	= Int("messagePaddingX");
	public static int MESSAGEPADDINGY	= Int("messagePaddingY");
	public static int MESSAGESPEED 		= Int("messageSpeed");
	public static int LINEHEIGHT 		= Int("lineHeight");
	public static boolean MINMEMMODE 	= Bool("minimalMemoryMode");
	public static boolean REFLECTIONS 	= Bool("reflections");
	public static boolean SHADOWS		= Bool("shadows");
	public static int WINDOWWIDTH		= Int("windowWidth");
	public static int WINDOWHEIGHT		= Int("windowHeight");
	public DIR[] DIRS = {DIR.DOWN,DIR.UP,DIR.LEFT,DIR.RIGHT,DIR.IDLE};
	public static Player DEFAULTPLAYER = Player.getInstance(
			Int("playerWidth"),
			Int("playerHeight"),
			String("playerImage"),
			Int("rows"),
			Int("cols"),
			Int("playerX"),
			Int("playerY"),
			Int("walkAnimationSpeed"),
			Int("idleAnimationSpeed"),
			Int("idleTimeOut"),
			Int("overlayLevel")
			);

	public static int ANIMSPEED = 20;

	public static int AVERAGEFPS ;

	

	public static Player getDefaultPlayer() {
		return DEFAULTPLAYER;
	}
	
	public static int Int(String key){
		int returnVal = 0;
		if(prefs.containsKey(key)){
			try{
				returnVal = Integer.parseInt(prefs.get(key));
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		 return returnVal;
	}
	public static Boolean Bool(String key){
		boolean returnVal = false;
		if(prefs.containsKey(key)){
			try{
				returnVal = Boolean.parseBoolean(prefs.get(key));
			}catch (Exception e) {
			}
		}
		 return returnVal;
	}
	
	public static String String(String key){
		if(prefs.containsKey(key)){
			return prefs.get(key);
		}
		else return null;
	}
	
	private static HashMap<String, String> loadPrefsFile() {
			File init = new File("conf/init.txt");
			HashMap <String,String> prefs = new HashMap<String, String>();
			BufferedReader red = null;
			try {
				red = new BufferedReader(new FileReader(init));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 String line = "";
			 String val = "";
			 Pattern p = Pattern.compile("^(\\S+)\\s?=\\s?(\"(.+)\"|(\\S+))$");
			 Matcher m = null;
			 try {
				while (( line = red.readLine()) != null){
					 m = p.matcher(line);
					 while(m.find()){
						if(m.group(3) != null){
							val = m.group(3);
						}
						else val = m.group(4);
						
						prefs.put(m.group(1), val);
					 }
				 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return prefs;
	}

	public static void initGffx(Graphics2D gffx) {
		gffx.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
		gffx.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
		gffx.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_SPEED);
		gffx.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		gffx.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_SPEED);
	}
		
	
}
