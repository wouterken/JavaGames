import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;


public class SallyCanvas extends JComponent{
	
	private SallyJump sallyJump;
	public static int Height = 400;
	public int Width = 400;
	
	public SallyCanvas(SallyJump sallyJump){
		super();
		setPreferredSize(new Dimension(Width,Height));
		this.sallyJump = sallyJump;
		setSize(Width,Height);
		}
	@Override
	public void paint(Graphics gffx) {
		sallyJump.draw(gffx);
	
		
	}

}
