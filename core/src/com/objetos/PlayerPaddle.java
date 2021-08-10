package com.objetos;

import com.badlogic.gdx.math.Vector2;
import com.utils.AABB;

public class PlayerPaddle extends Entity{

	public PlayerPaddle(Vector2 pos, int width, int height) {
		super(pos, width, height);
		bounds = new AABB(new Vector2(pos.x + width/2, pos.y + height/2),width+5,height+5);
	}

}
