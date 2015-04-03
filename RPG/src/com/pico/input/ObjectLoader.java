package com.pico.input;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.pico.objects.DrawableObject;
import com.pico.objects.WorldObject;

public class ObjectLoader {

	public static ArrayList<DrawableObject> loadObjects(HashMap<String, String> opts){
		ArrayList<DrawableObject> retVal = new ArrayList<DrawableObject>();
		
		for(String key: opts.keySet()){
			retVal.add(loadObject(opts.get(key),key));
		}
		
		return retVal;
	}

	private static WorldObject loadObject(String object,String pos) {
		File objectFile = new File("objects/"+object+".object");
		String[] position = pos.split(",");
		if(!objectFile.exists()){
			try {
				throw new IOException("Object "+object+".object Doesn't exist!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		HashMap<String, String> opts =  FileLoader.loadValuePairs(objectFile);
		
		return new WorldObject(
				opts.get("name"), 
				opts.get("description"), 
				Integer.parseInt(position[0]), 
				Integer.parseInt(position[1]), 
				Integer.parseInt(opts.get("width")), 
				Integer.parseInt(opts.get("height")),
				opts.get("image"),
				opts.get("mask"),
				Boolean.parseBoolean(opts.get("hasShadows")),
				Integer.parseInt(opts.get("level"))
				);
	}
}
