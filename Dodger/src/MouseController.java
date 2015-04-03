import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class MouseController implements MouseMotionListener, MouseListener {

	private PlayerBlock playerBlock;

	public MouseController(PlayerBlock playerBlock) {
		this.playerBlock = playerBlock;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		playerBlock.jump();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Game.lose = true;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		playerBlock.move(e.getPoint());
	}

}
