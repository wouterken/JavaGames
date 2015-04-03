import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;


public class GameCanvas extends Canvas{
	private BufferedImage offscreen;
	private Graphics offscreenGffx;
	public boolean drawing;
	public boolean repainting;
	private Image dbImage;
	private Graphics dbg;
	public GameCanvas(){
		super();
		offscreen = new BufferedImage(800,800,BufferedImage.TYPE_3BYTE_BGR);
		offscreenGffx = offscreen.createGraphics();
		setPreferredSize(new Dimension(800,800));
	}
	@Override
	public void paint(Graphics gffx) {
		if(!drawing && !repainting){
		repainting = true;
		gffx.drawImage(offscreen,0,0,this);
		repainting = false;
		}
	}
	public void update(Graphics g) 
    { 
         paint(g); 
    } 
	public Graphics getBrush(){
		return offscreenGffx;
	}
}
