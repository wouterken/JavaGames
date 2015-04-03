import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameKeyController implements KeyListener {

	private Game game;

	public GameKeyController(Game game){
		this.game = game;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT){
			moveLeft();
		}
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){
			moveRight();
		}
		if(arg0.getKeyCode() == KeyEvent.VK_UP){
			moveUp();
		}
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN){
			moveDown();
		}
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE){
			game.toggleDrawing();
		}
	}

	private void moveRight() {
		game.stepRight();
	}
	private void moveLeft(){
		game.stepLeft();
	}
	private void moveUp() {
		game.stepUp();
	}
	private void moveDown(){
		game.stepDown();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
