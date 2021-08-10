package com.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import com.managers.GameScreenManager;


public class Application extends Game{
	
	public static String APP_TITLE = "Ping Pong";
	public static double APP_VERSION = 0.1;
	public static int APP_WIDTH = 720;
	public static int APP_HEIGHT = 480;
	public static int APP_FPS = 100;
	
	public static int V_WIDTH = 720;
	public static int V_HEIGHT = 480;
	
	
	public AssetManager assets;
	public ShapeRenderer shapeBatch;
	public GameScreenManager gsm;
	
	public SpriteBatch batch;
	Texture img;
	
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeBatch = new ShapeRenderer();
		
		gsm = new GameScreenManager(this);
		shapeBatch.setAutoShapeType(true);
		assets = new AssetManager();
		
		
		
		
		
	}

	@Override
	public void render () {
		super.render();
//		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		batch.end();
		
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
			Gdx.app.exit();
		}
		Gdx.graphics.setTitle("FPS: " + String.valueOf(Gdx.graphics.getFramesPerSecond()));
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		shapeBatch.dispose();
		assets.dispose();
		
	}
}
