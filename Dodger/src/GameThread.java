
public class GameThread extends Thread {

	private Game game;
	private boolean newGame;

	public GameThread(Game game) {
		this.game = game;
	}

	public void run() {
		while(true){
			game.tick();
			try{sleep(10);}catch(Exception e){}
		}
	}


}
