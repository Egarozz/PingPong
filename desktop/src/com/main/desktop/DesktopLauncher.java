package com.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.main.Application;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Application.APP_TITLE + " " + Application.APP_VERSION;
		config.width = Application.APP_WIDTH;
		config.height = Application.APP_HEIGHT;
		config.foregroundFPS = Application.APP_FPS;
		config.backgroundFPS = Application.APP_FPS;
		config.resizable = false;
		new LwjglApplication(new Application(), config);
	}
}
