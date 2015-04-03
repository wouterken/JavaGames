package gamelogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
	
	private int width;
	private int height;
	private char[][] mapData;

	public Map(String fileName){
		File newMap = new File(fileName);
		Scanner mapConstructor;
		try {
			mapConstructor = new Scanner(newMap);
			String dimensions = mapConstructor.nextLine();
			width = Integer.parseInt(dimensions.substring(0,dimensions.indexOf('x')));
			height = Integer.parseInt(dimensions.substring(dimensions.indexOf('x')+1));
			mapData = new char[height][width];
			for(int y = 0; y<height; y++){
				String mapLine = mapConstructor.nextLine();
				mapData[y] = trimArray(width,mapLine.toCharArray());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private char[] trimArray(int width, char[] charArray) {
		char[] trimmed = new char[width];
		for(int i = 0; i< trimmed.length; i++){
			trimmed[i] = charArray[i];
		}
		return trimmed;
	}

	public char[][] getData() {
		// TODO Auto-generated method stub
		return mapData;
	}
}
