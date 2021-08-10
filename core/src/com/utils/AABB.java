package com.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class AABB {

	public Vector2 pos;
	public Vector2 previusPos;
	
	private float xOffset = 0;
	private float yOffset = 0;
	private float w;
	private float h;
	private float r;
	private int size;
	
	public AABB(Vector2 pos, int w, int h) {
		previusPos = new Vector2(pos.x-w/2, pos.y-h/2);
		pos.set(pos.x-w/2, pos.y-h/2);
		this.pos = pos;
		this.w = w;
		this.h = h;
		
		size = Math.max(w, h);
	}
	
	public AABB(Vector2 pos, int r) {
		this.pos = pos;
		this.r = r;
		this.size = r;
	}
	
	public void update(float x, float y) {
		
		
		previusPos.set(pos.x, pos.y);
	    pos.set(x-w/2, y-h/2);
	    
	    
	}
	
	public Vector2 getPos() {return pos;}
	public float getRadius() {return r;}
	public float getWidth() {return w;}
	public float getHeight() {return h;}
	
	public void setBox(Vector2 pos, int w, int h) {
		this.pos = pos;
		this.w = w;
		this.h = h;
		
		size = Math.max(w, h);
	}
	
	public void setCircle(Vector2 pos, int r) {
		this.pos = pos;
		this.r = r;
		
		
		size = r;
	}
	public void setWidth(int w) {this.w = w;}
	public void setHeight(int h) {this.h = h;}
	
	public void setXOffset(float f) {this.xOffset = f;}
	public void setYOffset(float f) {this.yOffset = f;}
		
	public boolean collides(AABB bBox) {
		float ax = ((pos.x + xOffset) + (w/2));
		float ay = ((pos.y + yOffset) + (h/2));
		float bx = (bBox.pos.x + (bBox.xOffset/2)) + (bBox.w/2);
		float by = (bBox.pos.y + (bBox.yOffset/2)) + (bBox.h/2);
		
		if(Math.abs(ax - bx) < (this.w/2) + (bBox.w/2)) {
			if(Math.abs(ay - by) < (this.h/2) + (bBox.h/2)) {
			return true;
			}
		}
		
		return false;
	}
	
	public void render(ShapeRenderer render) {
		render.begin(ShapeType.Line);
		render.setColor(Color.RED);
		render.rect(pos.x,pos.y,w,h);
		render.end();
	}
	
	
	
}
