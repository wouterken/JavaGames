package com.pico.input;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class ImageMap {

	public HashMap<String, HashMap<String,HashMap<String,BufferedImage>>> imageMap = new HashMap<String,HashMap<String,HashMap<String,BufferedImage>>>();
	private static ImageMap instance;
	
	public ImageMap(){
		imageMap.put("characters", CharacterLoader.loadCharacters());
	//	imageMap.put("objects	", ObjectLoader.loadObjects());
		imageMap.put("towns		", TownLoader.loadTowns());
	//	imageMap.put("worlds	", AreaLoader.loadWorlds());
		imageMap.put("maps		", MapLoader.loadMaps());
	}
	public HashMap<String,BufferedImage> character(String arg){
		return (HashMap)(imageMap.get("characters")).get(arg);
	}
	public HashMap<String,BufferedImage> object(String arg){
		return (HashMap)(imageMap.get("objects")).get(arg);
	}
	public HashMap<String,BufferedImage> town(String arg){
		return (HashMap)(imageMap.get("towns")).get(arg);
	}
	public static ImageMap getInstance(){
		if(instance == null){
			instance = new ImageMap();
		}
		return instance;
	}
}
