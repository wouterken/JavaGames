import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class GameFrame extends JFrame{

	private GameCanvas canvas;
	private Game game;
	public GameFrame GameFrame;
	private Image man;
	public GameFrame(Game game, GameCanvas canvas, int x, int y){
		super("Sally's Frame!");
		this.game = game;
		this.add(canvas);
		this.setSize(canvas.width,canvas.height);
		this.setResizable(false);
		this.setVisible(true);
		man = loadImage("Man.png",x,y);
		Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(man, new Point(0,0), "Man");
		this.setCursor(c);
		GameFrame = this;
		
	}
	private Image loadImage(String string,int x,int y) {
		Image baseImg = null;
		BufferedImage returnImg = new BufferedImage(x,y,BufferedImage.TYPE_4BYTE_ABGR);
		try{
			File imageFile = new File(string);
		 baseImg = ImageIO.read(imageFile);
		}catch(Exception e){
			System.out.println("Error loading image");
		}
		returnImg.getGraphics().drawImage(baseImg,0,0,x,y,null);
		return returnImg;
	}
}
