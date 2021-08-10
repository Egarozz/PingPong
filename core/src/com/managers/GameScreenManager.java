package com.managers;

import java.util.HashMap;

import com.main.Application;
import com.screens.AbstractScreen;
import com.screens.GameScreen;

public class GameScreenManager {
	
	public final Application app;
	
	private HashMap<STATE, AbstractScreen> gameScreens;
	
	public enum STATE{
		PLAY, MAIN_MENU, SETTING;
	}

	public GameScreenManager(Application app) {
		this.app = app;
		
		initGameScreen();
		setScreen(STATE.PLAY);
	}
	
	public void initGameScreen() {
		this.gameScreens = new HashMap<>();
		gameScreens.put(STATE.PLAY, new GameScreen(app));
	}
	
	public void setScreen(STATE state) {
		app.setScreen(gameScreens.get(state));
	}
	
	public void dispose() {
		for(AbstractScreen screen: gameScreens.values()) {
			if(screen != null) {
				screen.dispose();
			}
		}
	}
	
	
	

}
