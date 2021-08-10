package com.objetos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.utils.AABB;

public abstract class Entity {
	
	protected Vector2 pos;
	
	public static boolean debug = false;
	
	protected AABB bounds;
	protected int width;
	protected int height;
	
	public Entity(Vector2 pos, int width, int height) {
		this.pos = pos;
		
		this.width = width;
		this.height = height;
	}
	
	
	public void render(ShapeRenderer render) {
		bounds.update(pos.x + width/2, pos.y + height/2);
		
		render.begin(ShapeType.Filled);
		render.setColor(Color.WHITE);
		render.rect(pos.x, pos.y, width, height);
		render.end();
		
		if(debug) {
			bounds.render(render);
		}
	}
	
	public void move(int x, int y) {
		this.pos.x += x;
		this.pos.y += y;
	}
	
	public AABB getAABB() {return bounds;}

}
