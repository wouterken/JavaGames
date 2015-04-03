package com.pico.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class EventBus {
	private static EventBus instance;
	private static HashMap<String,ArrayList<EventListener>> eventsList = new HashMap<String, ArrayList<EventListener>>();

	private EventBus(){
		
	}
	public static void Fire(GameEvent event){
		String key = event.getClass().toString();
		ArrayList<EventListener>listenerList = eventsList.get(key);
		for(EventListener listener : listenerList){
			listener.fire(event);
		}
	}
	
	public static void registerListener(GameEvent event, EventListener object){
		String className = event.getClass().toString();
		if(!eventsList.containsKey(className)){
			eventsList.put(className, new ArrayList<EventListener>(Arrays.asList(object)));
		}
		eventsList.get(className).add(object);
	}
	
	public static EventBus getInstance(){
		if(instance == null){
			instance = new EventBus();
		}
		return instance;
	}
}
