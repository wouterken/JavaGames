import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class SallyKeyController implements KeyListener{

	private Sally sally;

	public SallyKeyController(Sally sally){
		this.sally = sally;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE){
			sally.flap();
		}
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT){
			sally.moveLeft();
		}
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){
			sally.moveRight();
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
	
	
}
