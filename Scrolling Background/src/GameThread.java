
public class GameThread extends Thread {

	private Game game;
	private boolean newGame;

	public GameThread(Game game) {
		this.game = game;
	}

	public void run() {
		while(true){
			if(!newGame){
			game.refresh();
			try{} catch(Exception e){}
			} else{
				for(int i = 5; i>0; i--){
					game.showEndGameMessage(i);
					try{sleep(500);}catch (Exception e) {}
				}
				newGame = false;
				game.reset();
			}
			
		}
	}

	public void setNewGame() {
		newGame = true;
	}

}
