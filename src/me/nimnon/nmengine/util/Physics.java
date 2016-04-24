package me.nimnon.nmengine.util;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.entity.GameObject;
import me.nimnon.nmengine.entity.Group;
import me.nimnon.nmengine.entity.tile.TileMap;

public class Physics {

	/**
	 * Checks to see if two GameObjects intersect, if so, method returns true
	 * 
	 * @param o1
	 *            GameObject 1
	 * @param o2
	 *            GameObject 2
	 * @return boolean Do the GameObjects intersect?
	 */
	public static boolean overlaps(GameObject o1, GameObject o2) {
		if (!o1.equals(o2)) {
			if (o1.x + o1.width > o2.x && o1.x < o2.x + o2.width) {
				if (o1.y + o1.height > o2.y && o1.y < o2.y + o2.height) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Does an overlap check on two GameObjects and separates as needed, if a
	 * separation is performed, method returns true.
	 * 
	 * @param o1
	 *            GameObject 1
	 * @param o2
	 *            GameObject 2
	 * @return boolean Did the objects get separated?
	 */
	public static boolean collide(GameObject o1, GameObject o2) {
		if (overlaps(o1, o2)) {

			double l = (o1.x + o1.width) - o2.x;
			double r = o1.x - (o2.x + o2.width);
			double t = (o1.y + o1.height) - o2.y;
			double b = o1.y - (o2.y + o2.height);

			double deltaX = (Math.abs(l) > Math.abs(r)) ? r : l;
			double deltaY = (Math.abs(t) > Math.abs(b)) ? b : t;

			double relVelX = o1.velocity.x - o2.velocity.x;
			double relVelY = o1.velocity.y - o2.velocity.y;

			if (Math.abs(deltaX) < Math.abs(deltaY)) {
				if (o1.movable && o2.movable) {
					o1.x -= deltaX;
					if ((deltaX > 0 && relVelX > 0) || (deltaX < 0 && relVelX < 0))
						o1.velocity.x = -o1.velocity.x * o1.elasticity;

					o2.x += deltaX;
					if ((deltaX > 0 && relVelX > 0) || (deltaX < 0 && relVelX < 0))
						o2.velocity.x = -o2.velocity.x * o2.elasticity;

				} else if (o1.movable) {
					o1.x -= deltaX;
					o1.velocity.x = -o1.velocity.x * o1.elasticity;
				} else if (o2.movable) {
					o2.x += deltaX;
					o2.velocity.x = -o2.velocity.x * o2.elasticity;
				}

			} else {

				if (o1.movable && o2.movable) {
					o1.y -= deltaY * 0.5;
					if (deltaY < 0) {
						o1.touching[0] = true;
						o2.touching[1] = true;
					} else {
						o1.touching[1] = true;
						o2.touching[0] = true;
					}
					if ((deltaY > 0 && relVelY > 0) || (deltaY < 0 && relVelY < 0))
						o1.velocity.y = -o1.velocity.y * o1.elasticity;
					if (deltaY < 0)
						o2.x += o1.velocity.x / Game.ticksPerSecond;

					o2.y += deltaY * 0.5;
					if ((deltaY > 0 && relVelY > 0) || (deltaY < 0 && relVelY < 0))
						o2.velocity.y = -o2.velocity.y * o2.elasticity;

					if (-deltaY < 0)
						o1.x += o2.velocity.x / Game.ticksPerSecond;

				} else if (o1.movable) {

					if (deltaY < 0)
						o1.touching[0] = true;
					else
						o1.touching[1] = true;

					o1.y -= deltaY;
					if ((deltaY > 0 && relVelY > 0) || (deltaY < 0 && relVelY < 0))
						o1.velocity.y = -o1.velocity.y * o1.elasticity;
					if (-deltaY < 0)
						o1.x += o2.velocity.x / Game.ticksPerSecond;

				} else if (o2.movable) {

					if (deltaY > 0)
						o2.touching[0] = true;
					else
						o2.touching[1] = true;

					o2.y += deltaY;
					if ((deltaY > 0 && relVelY > 0) || (deltaY < 0 && relVelY < 0))
						o2.velocity.y = -o2.velocity.y * o2.elasticity;
					if (deltaY < 0)
						o2.x -= o1.velocity.x / Game.ticksPerSecond;
				}

			}
			return true;
		}
		return false;
	}

	public static boolean collide(GameObject o1, Group g1) {
		boolean returnValue = false;

		for (int i = 0; i < g1.getChildren().size(); i++) {
			if (g1.getChildren().get(i) instanceof GameObject) {
				if (!returnValue) {
					returnValue = collide(o1, (GameObject) g1.getChildren().get(i));

				} else {
					collide(o1, (GameObject) g1.getChildren().get(i));
				}
			} else if (g1.getChildren().get(i) instanceof Group) {
				if (!returnValue) {
					returnValue = collide(o1, (Group) g1.getChildren().get(i));

				} else {
					collide(o1, (Group) g1.getChildren().get(i));
				}
			}
		}
		return returnValue;
	}

	public static boolean collide(Group g1, GameObject o1) {
		return collide(o1, g1);
	}

	public static boolean collide(Group g1, Group g2) {
		boolean returnValue = false;

		for (int i = 0; i < g1.getChildren().size(); i++) {
			if (g1.getChildren().get(i) instanceof GameObject) {
				if (!returnValue) {
					returnValue = collide((GameObject) g1.getChildren().get(i), g2);
				} else {
					collide((GameObject) g1.getChildren().get(i), g2);

				}
			} else if (g1.getChildren().get(i) instanceof Group) {
				if (!returnValue) {
					returnValue = collide((Group) g1.getChildren().get(i), g2);
				} else {
					collide((Group) g1.getChildren().get(i), g2);
				}
			}
		}

		return returnValue;
	}
	
	public static boolean collide(GameObject o1, TileMap g1) {
		boolean returnValue = false;

		for (int i = 0; i < g1.getChildren().length; i++) {
			if (g1.getChildren()[i] instanceof GameObject) {
				if (!returnValue) {
					returnValue = collide(o1, (GameObject) g1.getChildren()[i]);

				} else {
					collide(o1, (GameObject) g1.getChildren()[i]);
				}
			}
		}
		return returnValue;
	}
	
	public static boolean collide(Group g1, TileMap g2) {
		boolean returnValue = false;
		for (int i = 0; i < g1.getChildren().size(); i++) {
			if (g1.getChildren().get(i) instanceof GameObject) {
				if (!returnValue) {
					returnValue = collide((GameObject)g1.getChildren().get(i), g2);

				} else {
					collide((GameObject)g1.getChildren().get(i), g2);
				}
			}
		}
		return returnValue;
	}

}
