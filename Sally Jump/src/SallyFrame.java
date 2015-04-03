import java.awt.Graphics;

import javax.swing.JFrame;


public class SallyFrame extends JFrame{
	private SallyCanvas AmazingCanvas;
	
	public SallyFrame(SallyJump sallyJump){
		super("Sally's Frame!");
		AmazingCanvas = new SallyCanvas(sallyJump);
		add(AmazingCanvas);
		setResizable(false);
		setVisible(true);
		SallyThread AmazingThread = new SallyThread(AmazingCanvas,sallyJump);
		pack();
		AmazingThread.start();
	}

}
