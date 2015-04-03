package com.pico.logic.locations;

import java.util.List;

import com.pico.objects.WorldObjectData;

public class AreaData {

	public String backgroundImageName;
	public String foregroundImageName;
	public String maskImageName;
	public List<WorldObjectData> objects;

	public AreaData(String _backgroundImageName, String _foregroundImageName, String _maskImageName, List<WorldObjectData> _objects){
		backgroundImageName = _backgroundImageName;
		foregroundImageName = _foregroundImageName;
		maskImageName 		= _maskImageName;
		objects 		 	= _objects;
	}
}
