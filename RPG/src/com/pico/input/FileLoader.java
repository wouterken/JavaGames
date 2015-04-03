package com.pico.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileLoader {

	public static HashMap<String,String> loadValuePairs(File file){
		HashMap <String,String> values = new HashMap<String, String>();
		BufferedReader red = null;
		try {
			red = new BufferedReader(new FileReader(file));
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
					
					values.put(m.group(1), val);
				 }
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return values;
	}
}
