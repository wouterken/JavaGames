package com.pico.display;

import com.pico.system.Game;
import com.pico.system.GameThread;

public class PainterThread extends Thread {


	private static PainterThread _instance;
	private static GameCanvas canvas;

	public static PainterThread getInstance(){
		if(_instance == null){
			_instance = new PainterThread();
		}
		return _instance;
	}

	public static void init(GameCanvas _canvas){
		canvas = _canvas;
		PainterThread.getInstance().start();
	}

	public void run() {
		while(true){
			canvas.repaint();
			try{sleep(1);}catch (Exception e) {System.out.println(e + " in thread");
			}
		}
	}


}
