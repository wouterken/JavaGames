package display;

import java.awt.Graphics;
import java.awt.Image;

import prefs.PRFS;

public class MapEditor implements Paintable {
	
	private Image mapImage = null;

	public MapEditor(){
		
	}

	@Override
	public void draw(Graphics paintBrush) {
		paintBrush.setFont(paintBrush.getFont().deriveFont(30f));
		paintBrush.drawString("MAPEDITOR", 300, 300);
	}
}
