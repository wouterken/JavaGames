package gamelogic;
import gamelogic.entities.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;


public class GameKeyController implements KeyListener {
	public enum direction {LEFT,RIGHT,UP,DOWN};

	private Player player;
	private ArrayList<String> keysDown = new ArrayList<String>();
	private String lastKeysDown ="";
	private boolean calculatingKeys;
	private Game game;
	public long lastTime; 
	public GameKeyController(Player player, Game game){
		this.player = player;
		this.game = game;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		arg0.setKeyChar('p');
		keyReleased(arg0);
		System.out.println("Key press");

	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		System.out.println("Key release");
		while(calculatingKeys){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT){
			if(keysDown.contains("l")){
				keysDown.remove("l");
			}else if(arg0.getKeyChar() == 'p')
				keysDown.add("l");
		}
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){
			if(keysDown.contains("r")){
				keysDown.remove("r");
			}else if(arg0.getKeyChar() == 'p')
				keysDown.add("r");
		}
		if(arg0.getKeyCode() == KeyEvent.VK_UP){
			if(keysDown.contains("up")){
				keysDown.remove("up");
			}else if(arg0.getKeyChar() == 'p')
				keysDown.add("up");
		}
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN){
			if(keysDown.contains("down")){
				keysDown.remove("down");
			}else if(arg0.getKeyChar() == 'p')
				keysDown.add("down");
		}
		if(arg0.getKeyCode() == KeyEvent.VK_C){
			if(keysDown.contains("c")){
				keysDown.remove("c");
			}else if(arg0.getKeyChar() == 'p')
				keysDown.add("c");
		}
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE){
			if(keysDown.contains("jump")){
				keysDown.remove("jump");
			}else if(arg0.getKeyChar() == 'p')
				keysDown.add("jump");
		}
		if(arg0.getKeyCode() == KeyEvent.VK_CONTROL){
			if(keysDown.contains("shoot")){
				keysDown.remove("shoot");
			}else if(arg0.getKeyChar() == 'p')
				keysDown.add("shoot");
		}
		if(arg0.getKeyCode() == KeyEvent.VK_M){
			if(arg0.isAltDown()){
				if(keysDown.contains("switch")){
					keysDown.remove("switch");
			}
			else if(arg0.getKeyChar() == 'p')
				keysDown.add("switch");
			}
		}
		if(arg0.getKeyCode() == KeyEvent.VK_S){
				if(keysDown.contains("ChangeBullets")){
					keysDown.remove("ChangeBullets");
			}
			else if(arg0.getKeyChar() == 'p')
				keysDown.add("ChangeBullets");
			
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	public void calculateKeyPresses() {
	   lastTime = System.currentTimeMillis(); 
		try{
			if(calculatingKeys == true) return;
		
		calculatingKeys = true;
		for(String str: keysDown){
			if(str.equals("l")){
				player.move(direction.LEFT);
			}
			if(str.equals("r")){
				player.move(direction.RIGHT);
			}
			if(str.equals("jump")){
				player.move(direction.UP);
			}
			if(str.equals("up")){
				player.lookUp();
			}
			if(str.equals("down")){
				player.lookDown();
			}
			if(str.equals("c")){
				player.move(direction.DOWN);
			}
			if(str.equals("d")){
				player.move(direction.DOWN);
			}
			if(str.equals("shoot")){
				player.shoot();
			}
			if(str.equals("switch")){
				game.changeScreen(Screen.MAPEDIT);
			}
			if(str.equals("ChangeBullets")){
				player.changeBullets();
			}
		}
		if(!lastKeysDown.equals(keysDown.toString().trim())){
		}
		lastKeysDown = keysDown.toString().trim();
		calculatingKeys = false;
	}catch (ConcurrentModificationException e) {
		game.resetKeyController();
		System.out.println(e + "in calculating key presses");
	}
	
	}
}
