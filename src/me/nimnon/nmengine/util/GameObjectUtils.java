package me.nimnon.nmengine.util;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.entity.GameObject;

public class GameObjectUtils {
	public static boolean collideWithGameEdge(GameObject o) {
		boolean ret = false;
		//solveX
		if(o.x < Game.activeCamera.x || o.x > Game.activeCamera.x + Game.activeCamera.width - o.width) {
			ret = true;
			o.x = Math.max(Math.min(o.x, Game.activeCamera.x+ Game.activeCamera.width -o.width), Game.activeCamera.x);
			o.velocity.x = -o.velocity.x * o.elasticity;
		}
		if(o.y < Game.activeCamera.y || o.y > Game.activeCamera.y + Game.activeCamera.height - o.height) {
			ret = true;
			o.y = Math.max(Math.min(o.y, Game.activeCamera.y+ Game.activeCamera.height-o.height), Game.activeCamera.y);
			o.velocity.y = -o.velocity.y * o.elasticity;
		}
		return ret;
	}
}
