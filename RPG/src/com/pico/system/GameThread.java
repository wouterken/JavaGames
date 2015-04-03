package com.pico.system;

public class GameThread extends Thread {

	private static Game _game;
	private static GameThread _instance;

	public static GameThread getInstance(){
		if(_instance == null){
			_instance = new GameThread();
		}
		return _instance;
	}

	public static void init(Game game){
		_game = game;
		GameThread.getInstance().start();
	}

	public void run() {
		while(true){
			_game.refresh();
			try{sleep(10);}catch (Exception e) {System.out.println(e + " in thread");
			}
		}
	}


}
