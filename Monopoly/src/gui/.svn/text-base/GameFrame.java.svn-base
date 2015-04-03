package gui;

import java.awt.*;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	private final GameCanvas canvas;
	
	public GameFrame(String title,GameCanvas _canvas, KeyListener... keys){
		super(title);
		canvas = _canvas;
		setBackground(Color.GRAY);
		setLayout(new BorderLayout());
		for(KeyListener k: keys){
			canvas.addKeyListener(k);
		}
		add(canvas,BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		
		setResizable(false);
		pack();
		
		setVisible(true);
		canvas.requestFocus();
	}
	public void repaint() {
	canvas.repaint();
		
	}

}
