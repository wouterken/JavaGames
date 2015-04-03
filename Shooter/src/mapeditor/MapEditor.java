package mapeditor;

import java.awt.Graphics;

import display.Paintable;

public class MapEditor implements Paintable{
		public MapEditor(){
			
		}

		@Override
		public void draw(Graphics paintBrush) {
			paintBrush.setFont(paintBrush.getFont().deriveFont(30f));
			paintBrush.drawString("MAPEDITOR", 300, 300);
		}
}
