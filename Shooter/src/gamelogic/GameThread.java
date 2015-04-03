package gamelogic;

public class GameThread extends Thread {

	private Game game;
	
	public GameThread(Game game) {
		this.game = game;
	}


	public void run() {
		while(true){
			this.game.refresh();
			try{sleep(10);}catch (Exception e) {System.out.println(e + " in thread");
			}
		}
	}


}
