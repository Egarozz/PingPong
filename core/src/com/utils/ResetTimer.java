package com.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer.Task;
import com.objetos.Ball;

public class ResetTimer extends Task{
    
	boolean lose;
	Ball ball;
	
	public ResetTimer(boolean lose, Ball ball) {
		this.lose = lose;
		this.ball = ball;
	}
	@Override
	public void run() {
		if(lose) {
			ball.getDirection().set(-1,0);
		}else {
			ball.getDirection().set(1,0);
		}
		
		
		ball.setVelocity(2);	
	}
	
	public void setLose(boolean lose) {
		this.lose = lose;
	}
	

}
