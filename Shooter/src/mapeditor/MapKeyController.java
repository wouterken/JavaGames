package mapeditor;

import gamelogic.Game;
import gamelogic.Screen;
import gamelogic.GameKeyController.direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class MapKeyController implements KeyListener {

	private Game game;
	private ArrayList<String> keysDown = new ArrayList<String>();
	private boolean calculatingKeys;
	
	public MapKeyController(Game game){
		this.game = game;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
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
		if(arg0.getKeyCode() == KeyEvent.VK_G){
			if(arg0.isAltDown()){
				if(keysDown.contains("switch")){
					keysDown.remove("switch");
			}
			else if(arg0.getKeyChar() == 'p')
				keysDown.add("switch");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void calculateKeyPresses() {
		try{
			if(calculatingKeys == true) return;
		
		calculatingKeys = true;
		for(String str: keysDown){
			if(str.equals("l")){
	
			}
			if(str.equals("r")){
				
			}
			
			if(str.equals("up")){
				
			}
			if(str.equals("down")){
				
			}
		
			if(str.equals("switch")){
				game.changeScreen(Screen.GAME);
			}
		}
		
		calculatingKeys = false;
	}catch (ConcurrentModificationException e) {
		System.err.println(e);
		
	}
	
	}

}
