package com.pico.objects.characters.player;


public enum DIR {
	DOWN(0),UP(1),LEFT(2),RIGHT(3),IDLE(4);
	
	 private int code;

	 private DIR(int c) {
	   code = c;
	 }

	 public int value() {
	   return code;
	 }
}

