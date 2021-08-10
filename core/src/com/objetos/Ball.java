package com.objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.utils.AABB;
import com.utils.ResetTimer;

public class Ball extends Entity{
    
	private float velocity = 2;
	private Vector2 direction;
	
	private ResetTimer timer;
	
	private int topBounce = 2;
	private int bounce = 0;
	
	
	public Ball(Vector2 pos, int width, int height) {
		super(pos, width, height);
		direction = new Vector2(1,0);
		bounds = new AABB(new Vector2(pos.x + width/2, pos.y + height/2),width+3,height+3);
		timer = new ResetTimer(false, this);
	}
    
	public void setPosition(float x, float y) {
		pos.set(x,y);
	}
	@Override
	public void render(ShapeRenderer render) {
        
		
		render.begin(ShapeType.Filled);
		
		render.setColor(new Color(1,1,1,.3f));
		render.circle(pos.x+width/2, pos.y+width/2, width/2);
		//render.rect(pos.x, pos.y, width, height);
		render.end();
		
		if(debug) {
			bounds.render(render);
		}
	}
	
	public void update(float delta) {
		
		bounds.update(pos.x + width/2, pos.y + height/2);

        pos.add(direction.nor().scl(velocity));
		
		
	}
	public Vector2 getPosition() {return pos;}
	
	public void bouncePaddle(AABB box) {
		bounce++;
		if(bounce > topBounce) {
			velocity += .5f;
			System.out.println(velocity);
			bounce = 0;
			
			
		}
		
		float position = (box.pos.y + box.getHeight()/2) - (bounds.pos.y + bounds.getHeight()/2);
		float map = ((position + box.getHeight()/2)/(box.getHeight()/2+box.getHeight()/2))*(30+30)-30;

		
		pos.x = box.getPos().x + box.getWidth() + 15;
		direction.setAngleDeg(-map);
	}
	public void bounceEnemy(EnemyPaddle enemy) {
		AABB box = enemy.getAABB();
		
		bounce++;
		if(bounce > topBounce) {
			velocity += .5f;
			bounce = 0;
			enemy.addVelocity();
			System.out.println(velocity);
		}
		
		float position = (box.pos.y + box.getHeight()/2) - (bounds.pos.y + bounds.getHeight()/2);
		float map = ((position + box.getHeight()/2)/(box.getHeight()/2+box.getHeight()/2))*(30+30)-30;

		
		pos.x = box.getPos().x - box.getWidth() - 15;
		direction.setAngleDeg(-180-map);
	}
	
	public void bounceBounds() {
		direction.set(direction.x, direction.y*-1);
	}
	public void bounceLateral() {
		direction.set(direction.x*-1, direction.y);
	}
	public void reiniciarBola(float x, float y, boolean lose) {
		pos.set(x,y);
		velocity = 0;
		if(lose) {
			timer.setLose(lose);
			Timer.schedule(timer, 1); 
			
		}else {
			timer.setLose(lose);
			Timer.schedule(timer, 1); 
		}
		     
	}

	public float getVelocity() {
		return velocity;
	}

	public Vector2 getDirection() {
		return direction;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	
	
				
			
		
	
	
	
	
	

}
