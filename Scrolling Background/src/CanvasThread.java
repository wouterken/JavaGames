
public class CanvasThread extends Thread{
	private GameCanvas canvas;

	public CanvasThread(GameCanvas canvas){
		this.canvas = canvas;
	}
	@Override
	public void run() {
	while(true){
		canvas.repaint();
		}
	}

}
