package com.pico.logic;

import com.pico.prefs.PREFS;

public class Info {
	
	public static void print(String str){
		if(!PREFS.DEBUG)
			return;
				
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		System.out.printf("%-45.45s || "+str+"\n",stack[2]);
	}
}
