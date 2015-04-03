package com.pico.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import com.pico.logic.Info;
import com.pico.system.Game;

public class KeyController implements KeyListener{

	private static KeyController instance;
	private ArrayList<KeyListener> listeners = new ArrayList<KeyListener>();
	private boolean removeAll;
	private ArrayList<KeyListener> toAdd = new ArrayList<KeyListener>();
	private ArrayList<KeyListener> toRemove = new ArrayList<KeyListener>();
	

	private KeyController(){
		
	}
	
	public static KeyController getInstance(){
		if(instance == null){
			instance = new KeyController();
		}
		return instance;
	}
	public void registerListener(KeyListener list){
		toAdd.add(list);
	}
	public void removeListener(KeyListener list){
		toRemove.add(list);
	}
	public ArrayList<KeyListener> clearAll(){
		ArrayList<KeyListener> returnVals = (ArrayList<KeyListener>) listeners.clone();
		removeAll = true;
		return returnVals;
	}
	public void addAll(ArrayList<KeyListener> list){
		toAdd.addAll(list);
	}

	public void toDo() {
		if(removeAll == true){
			listeners.clear();
			removeAll = false;
		}
		if(toAdd.size() > 0){
			for(KeyListener k: toAdd){
				listeners.add(k);
			}
			toAdd.clear();
		}
		if(toRemove.size() > 0){
			for(KeyListener k: toAdd){
				while(listeners.contains(k)){
					listeners.remove(k);
				}
			}
			toRemove.clear();
		}
	}
	public void keyPressed(KeyEvent evt) {
		toDo();
		if(evt.getKeyCode() == KeyEvent.VK_CAPS_LOCK){
			Game.getInstance().setNewWorld();
		}
		for(KeyListener list: listeners){
			list.keyPressed(evt);
		}

	}


	public void keyReleased(KeyEvent evt) {
		toDo();
		for(KeyListener list: listeners){
			list.keyReleased(evt);
		}

	}

	public void keyTyped(KeyEvent evt) {
		toDo();
		for(KeyListener list: listeners){
			list.keyTyped(evt);
		}

	}

}
