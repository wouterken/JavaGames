package com.pico.input;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class CharacterLoader {

	public static HashMap<String, HashMap<String, BufferedImage>> loadCharacters(){
		HashMap<String,HashMap<String,BufferedImage>> characters = new HashMap<String, HashMap<String,BufferedImage>>();
		HashMap<String,BufferedImage> john = new HashMap<String, BufferedImage>();
		
		john.put("right", (BufferedImage) ImageLoader.loadImage("walk.png"));
		characters.put("john", john);
		return characters;
		
	}
}
