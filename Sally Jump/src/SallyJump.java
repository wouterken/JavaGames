import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public class SallyJump {
	
	public static ArrayList<Platform> platforms = new ArrayList<Platform>();
	private Sally mainChar;
	private Background background;
	public SallyJump(Sally mainChar){
		addStartingPlatforms();
		this.mainChar = mainChar;
		this.background = new Background(this);
	}
	private void addStartingPlatforms() {
	for(int i = 0 ;i<10; i++){
			platforms.add(new Platform(30*(int)(Math.random()*10), i*50));
		}
	}
	public Sally getChar(){
		return mainChar;
	}
	public void draw(Graphics gffx){
		background.draw(gffx);
		
		for(Platform p: platforms){
			p.draw(gffx);
		}
		mainChar.draw(gffx);
	}
	public static void main(String[] args) {
		Sally mainChar = new Sally(200,100);
		SallyKeyController sallyController = new SallyKeyController(mainChar);
		SallyFrame GameFrame = new SallyFrame(new SallyJump(mainChar));
		GameFrame.addKeyListener(sallyController);
	}
}
