import javax.swing.JFrame;


public class GameFrame extends JFrame{

	private GameCanvas canvas;
	private Game game;
	public GameFrame GameFrame;
	public GameFrame(Game game){
		super("Sally's Frame!");
		this.game = game;
		this.addKeyListener(new GameKeyController(game));
		this.add(game.getCanvas());
		this.setSize(800,800);
		this.setResizable(false);
		this.setVisible(true);
		GameFrame = this;
		
	}
}
