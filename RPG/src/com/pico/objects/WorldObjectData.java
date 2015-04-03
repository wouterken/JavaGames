package com.pico.objects;

import java.awt.Point;

public class WorldObjectData {

	private String name;
	private String description;
	private int x;
	private int y;
	private int width;
	private int height;
	private String imageName;
	private String maskName;
	private Boolean hasShadow;

	public WorldObjectData(String _name, String _description, Point _location, Point _dimensions, String _imageName, String _maskName, Boolean _hasShadow){
		name		 = _name;
		description  = _description;
		x			 = _location.x;
		y 			 = _location.y;
		width		 = _dimensions.x;
		height		 = _dimensions.y;
		imageName	 = _imageName;
		maskName	 = _maskName;
		hasShadow = _hasShadow;
	}
}
