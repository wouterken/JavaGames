package com.pico.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.pico.display.Controller;
import com.pico.display.GameCanvas;
import com.pico.display.screens.GameMenu;
import com.pico.display.screens.Message;
import com.pico.events.EventBus;
import com.pico.events.LeftEvent;
import com.pico.events.RightEvent;
import com.pico.objects.characters.player.Player;
import com.pico.system.Game;

public class PlayerKeyController implements KeyListener {

	private Player player;

	public PlayerKeyController(Player _player){
		player = _player;
	}
	public void keyPressed(KeyEvent k) {
		do{
		if(k.getKeyCode() == KeyEvent.VK_UP){
			break;
		}
		if(k.getKeyCode() == KeyEvent.VK_DOWN){
			break;
		}
		if(k.getKeyCode() == KeyEvent.VK_LEFT){
			EventBus.Fire(new LeftEvent());
			break;
		}
		if(k.getKeyCode() == KeyEvent.VK_RIGHT){
			break;
		}
		if(k.getKeyCode() == KeyEvent.VK_A){
			player.activate();
		}
		if(k.getKeyCode() == KeyEvent.VK_M){
			Controller.getInstance().setScreen(new Message(new String[]{"Woohoo this is my message","Isn't this great!","I Think it's bloody fantastic!"}));

		}
		if(k.getKeyCode() == KeyEvent.VK_Q){
			System.out.println("here");
			Controller.getInstance().setScreen(Game.gameMenu);
		}
		if(k.getKeyCode() == KeyEvent.VK_E){
			System.out.println("here");
			Controller.getInstance().setScreen(Game.gameDisplay);
		}
		return;
		}while(false);
		player.movement.add(k.getKeyCode());
	}

	public void keyReleased(KeyEvent k) {
		while(player.movement.contains(k.getKeyCode())){
			player.movement.remove(new Integer(k.getKeyCode()));
		}
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
