package com.pico.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pico.logic.locations.Area;
import com.pico.objects.DrawableObject;
import com.pico.objects.WorldObject;

public class AreaLoader {

	public static Area loadWorld(String areaName) throws IOException {
		File area = new File("areas/"+areaName+".area");
		File objectList = new File("objectLists/"+areaName+".objects");
		if(!area.exists()){
			throw new IOException("Area "+areaName+".area Doesn't exist!");
		}
		if(!objectList.exists()){
			throw new IOException("Object list "+areaName+".objects Doesn't exist!");
		}
		HashMap<String, String> prefSet = FileLoader.loadValuePairs(area);
		ArrayList<DrawableObject> objectSet = ObjectLoader.loadObjects(FileLoader.loadValuePairs(objectList));
		
		return new Area(prefSet,objectSet);
	}

}
