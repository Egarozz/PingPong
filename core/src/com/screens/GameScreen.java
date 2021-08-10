package com.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.World;
import com.main.Application;
import com.objetos.Ball;
import com.objetos.EnemyPaddle;
import com.objetos.Entity;
import com.objetos.PlayerPaddle;

import java.util.Random;
public class GameScreen extends AbstractScreen{
	
	OrthographicCamera camera;
	
	SpriteBatch batch;
	ShapeRenderer render;
	PlayerPaddle objeto;
	EnemyPaddle enemy;
	Ball ball;
	BitmapFont font;
	Sound soundHit;
	Sound soundWin;
	Sound soundLose;
	private boolean pause = true;
	private int enemyScore = 0;
	private int playerScore = 0;
  
	public GameScreen(Application app) {
		super(app);
		
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false, Application.V_WIDTH, Application.V_HEIGHT);
	    render = new ShapeRenderer();
	    render.setAutoShapeType(true);
	    render.setProjectionMatrix(camera.combined);
	    
	    batch = new SpriteBatch();
	    batch.setProjectionMatrix(camera.combined);
	    
	    objeto = new PlayerPaddle(new Vector2(20, camera.viewportHeight/2), 20, 80);
	    ball = new Ball(new Vector2(camera.viewportWidth/2, camera.viewportHeight/2), 15, 15);
	    enemy = new EnemyPaddle(new Vector2(camera.viewportWidth-50,camera.viewportHeight/2 - 40),20,80, ball);
	    
	    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("AtariSmall.ttf"));
	    FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	    parameter.size = 40;
	    font = generator.generateFont(parameter); // font size 12 pixels
	    generator.dispose(); // don't forget to dispose to avoid memory leaks!
	    
	    soundHit = Gdx.audio.newSound(Gdx.files.internal("Sounds/Hit.wav"));
	    soundLose = Gdx.audio.newSound(Gdx.files.internal("Sounds/Lose.wav"));
	    soundWin = Gdx.audio.newSound(Gdx.files.internal("Sounds/Win.wav"));
	    
	}

	
	@Override
	public void show() {
		
	}




	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		
		
	    keyboardInputs();
	    if(pause) {
	    	ball.update(delta);
	    }
	    
	    if(objeto.getAABB().collides(ball.getAABB())) {
	    	ball.bouncePaddle(objeto.getAABB());
	    	soundHit.play(1);
	    	
	    }
	    if(enemy.getAABB().collides(ball.getAABB())) {
	    	ball.bounceEnemy(enemy);
	    	soundHit.play(1);
	    	
	    }
	    
	    if(ball.getAABB().getPos().y > camera.viewportHeight-ball.getAABB().getHeight()) {
	    	ball.setPosition(ball.getPosition().x, Gdx.graphics.getHeight()-ball.getAABB().getHeight());
	    	ball.bounceBounds();
	    	soundHit.play();
	    }
	    if(ball.getAABB().getPos().y < 0) {
	    	ball.setPosition(ball.getPosition().x, 5);
	    	ball.bounceBounds();
	    	soundHit.play();
	    }
	    if(ball.getAABB().getPos().x < 0) {
	    	
	    	ball.setPosition(ball.getPosition().x+5, ball.getPosition().y);
	    	ball.reiniciarBola(camera.viewportWidth/2, camera.viewportHeight/2, true);
	    	enemyScore += 1;
	    	soundLose.play();
	    }
	    if(ball.getAABB().getPos().x > camera.viewportWidth - ball.getAABB().getWidth()) {
	    	playerScore += 1;
	    	ball.setPosition(ball.getPosition().x - 5, ball.getPosition().y);
	    	ball.reiniciarBola(camera.viewportWidth/2, camera.viewportHeight/2, false);
	    	soundWin.play();
	    }
		
	    enemy.update();
		stage.act(delta);
	}


	@Override
	public void render(float delta) {
		super.render(delta);
		render.begin(ShapeType.Line);
		
		render.line(camera.viewportWidth/2, camera.viewportHeight-100, camera.viewportWidth/2, 0);
		render.end();
		objeto.render(render);
		ball.render(render);
		enemy.render(render);
		
		batch.begin();
		
		font.draw(batch, "SCORE\n" + playerScore + " - " + enemyScore, (camera.viewportWidth/2)-60, camera.viewportHeight-20);
		batch.end();
		stage.draw();
	}


	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
				
	}
	
	public void keyboardInputs() {
		
		
		if(Gdx.input.isKeyPressed(Keys.W)) {
//			objeto.getAABB().setXOffset(-25);
//			objeto.getAABB().setYOffset(-25);
			if(objeto.getAABB().pos.y < Gdx.graphics.getHeight()-objeto.getAABB().getHeight()) {
				objeto.move(0, 2);
			}
		}
		if(Gdx.input.isKeyPressed(Keys.S)) {
//			objeto.getAABB().setXOffset(-25);
//			objeto.getAABB().setYOffset(25);
			if(objeto.getAABB().pos.y > 0) {
				objeto.move(0, -2);
			}
		}
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			pause = pause ? false : true;
			
		}
	}
	
	
	
	
	
	

}
