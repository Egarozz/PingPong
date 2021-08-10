package com.objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.utils.AABB;

public class EnemyPaddle extends Entity{
	private Ball ball;
	private float velocity = .6f;
	private Vector2 direction;
	
	public EnemyPaddle(Vector2 pos, int width, int height, Ball ball) {
		super(pos, width, height);
		bounds = new AABB(new Vector2(pos.x + width/2, pos.y + height/2),width+5,height+5);
		this.ball = ball;
		direction = new Vector2(0,0);
	}
	
	public void update() {
		float centerPaddle = pos.y + this.getAABB().getHeight()/2f;
		float centerBall = ball.getAABB().pos.y + ball.getAABB().getHeight()/2;
		float lerp = (centerBall) + (centerPaddle - centerBall)*.5f;
		
		
		direction.set(0,lerp);
		
	  
		if(centerPaddle - centerBall < -10) {
			pos.y += direction.nor().scl(velocity).y;
		}else if(centerPaddle - centerBall > 10){
			pos.y -= direction.nor().scl(velocity).y;
		}else {
			pos.y = pos.y;
		}
		
		
	}
	
	public void addVelocity() {
		velocity += .05f;
	}

}
