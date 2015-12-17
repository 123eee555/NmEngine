package me.nimnon.nmengine.entity.tile;

import java.awt.Color;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.entity.GameObject;

public class Tile extends GameObject{

	public boolean drawDebug = true;

	public Tile(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void draw() {
		for (int i = 0; i < Game.cameras.size(); i++) {
			if (Game.cameras.get(i).isOnScreen(this)) {
				if(drawDebug)
					Game.cameras.get(i).imageGraphics.setColor(Color.ORANGE);
					Game.cameras.get(i).imageGraphics.drawRect((int)x-(int)Game.cameras.get(i).x, (int)y-(int)Game.cameras.get(i).y, (int)Math.floor(width-1), (int)Math.floor(height-1));
			}
		}
			
	}

	@Override
	public void destroy() {
		// Destroy what? LOL
	}

}
