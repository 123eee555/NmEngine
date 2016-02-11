package me.nimnon.nmengine.util;

import java.util.ArrayList;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.entity.Basic;
import me.nimnon.nmengine.entity.GameObject;
import me.nimnon.nmengine.entity.Group;
import me.nimnon.nmengine.entity.tile.TileMap;

public class Physics {

	/**
	 * Returns true if objects intersect
	 * 
	 * @param object1
	 *            Object to move
	 * @param object2
	 *            Static object
	 * @return boolean Did the objects collide?
	 */
	public static boolean overlaps(Basic object1, Basic object2) {
		if (object1 instanceof Group) {
			Group group = (Group) object1;
			GameObject o2 = (GameObject) object2;
			for (int i = 0; i < group.getChildren().size(); i++) {
				if (group.getChildren().get(i) instanceof GameObject) {
					GameObject o1 = (GameObject) group.getChildren().get(i);

					if (overlaps(o1, o2)) {
						return true;
					}
				}
			}
		} else if (object2 instanceof Group) {
			Group group = (Group) object2;
			GameObject o1 = (GameObject) object1;
			for (int i = 0; i < group.getChildren().size(); i++) {
				if (group.getChildren().get(i) instanceof GameObject) {
					GameObject o2 = (GameObject) group.getChildren().get(i);

					if (overlaps(o1, o2)) {
						return true;
					}
				}
			}
		} else if (object1 instanceof GameObject && object2 instanceof GameObject) {

			GameObject o1 = (GameObject) object1;
			GameObject o2 = (GameObject) object2;

			if (o1.x + o1.width > o2.x && o1.x < o2.x + o2.width) {
				if (o1.y + o1.height > o2.y && o1.y < o2.y + o2.height) {
					return true;
				}
			}

		} else {
			System.out.println("I dont know how to handle this overlap");
		}
		return false;

	}

	/**
	 * Collides object1 and object2, returns true if they collided successfully.
	 * 
	 * 
	 * @param object1
	 *            Object to collide
	 * @param object2
	 *            Other Object to collide
	 * @return boolean
	 */
	public static boolean collide(Basic object1, Basic object2) {
		if (object1 instanceof TileMap) {
			return collide(((TileMap) object1).getTileGroup(), object2);
		} else if (object2 instanceof TileMap) {
			return collide(object1, ((TileMap) object2).getTileGroup());
		} else if (object1 instanceof Group && object2 instanceof Group) {
			boolean ret = false;
			Group group1 = (Group) object1;
			Group group2 = (Group) object2;
			
			for(int i = 0; i < group1.getChildren().size(); i++) {
				if(ret == false && collide(group1.getChildren().get(i),group2)) {
					ret = true;
				} else {
					collide(group1.getChildren().get(i),group2);
				}
				
			}
			return ret;
		} else if (object1 instanceof Group) {
			boolean ret = false;
			Group group = (Group) object1;
			ArrayList<Basic> nearest = new ArrayList<>();
			GameObject o1 = (GameObject) object2;
			double lastArea = Double.MAX_VALUE;
			for (int i = 0; i < group.getChildren().size(); i++) {
				if (group.getChildren().get(i) instanceof GameObject) {
					GameObject o2 = (GameObject) group.getChildren().get(i);

					if (overlaps(o1, o2)) {

						double top = (o1.y) - (o2.y + o2.height);
						double bottom = (o1.y + o1.height) - (o2.y);
						double left = (o1.x) - (o2.x + o2.width);
						double right = (o1.x + o1.width) - (o2.x);

						double deltaY = (Math.abs(top) > Math.abs(bottom)) ? Math.abs(bottom) : Math.abs(top);
						double deltaX = (Math.abs(left) > Math.abs(right)) ? Math.abs(right) : Math.abs(left);

						double area = Math.abs(deltaY * deltaX);

						if (area > lastArea) {
							nearest.add(0, o2);
						} else {
							nearest.add(o2);
						}

						lastArea = area;
					}
				}
			}
			for (int i = 0; i < nearest.size(); i++) {
				if (collide(object1, nearest.get(i)))
					ret = true;
			}
			return ret;
		} else if (object2 instanceof Group) {
			boolean ret = false;
			Group group = (Group) object2;
			ArrayList<Basic> nearest = new ArrayList<>();
			GameObject o1 = (GameObject) object1;
			double lastArea = Double.MAX_VALUE;
			for (int i = 0; i < group.getChildren().size(); i++) {
				if (group.getChildren().get(i) instanceof GameObject) {
					GameObject o2 = (GameObject) group.getChildren().get(i);

					if (overlaps(o1, o2)) {

						double top = (o1.y) - (o2.y + o2.height);
						double bottom = (o1.y + o1.height) - (o2.y);
						double left = (o1.x) - (o2.x + o2.width);
						double right = (o1.x + o1.width) - (o2.x);

						double deltaY = (Math.abs(top) > Math.abs(bottom)) ? Math.abs(bottom) : Math.abs(top);
						double deltaX = (Math.abs(left) > Math.abs(right)) ? Math.abs(right) : Math.abs(left);

						double area = Math.abs(deltaY * deltaX);

						if (area > lastArea) {
							nearest.add(0, o2);
						} else {
							nearest.add(o2);
						}

						lastArea = area;
					}
				}
			}
			for (int i = 0; i < nearest.size(); i++) {
				if (collide(object1, nearest.get(i)))
					ret = true;
			}
			return ret;
		} else if (object1 instanceof GameObject && object2 instanceof GameObject) {

			GameObject o1 = (GameObject) object1;
			GameObject o2 = (GameObject) object2;

			if (overlaps(o1, o2)) {

				double top = (o1.y) - (o2.y + o2.height);
				double bottom = (o1.y + o1.height) - (o2.y);
				double left = (o1.x) - (o2.x + o2.width);
				double right = (o1.x + o1.width) - (o2.x);

				double deltaY = (Math.abs(top) > Math.abs(bottom)) ? bottom : top;
				double deltaX = (Math.abs(left) > Math.abs(right)) ? right : left;

				// double timeToX = (o1.velocity.x / Game.ticksPerSecond) /
				// deltaX;
				// double timeToY = (o1.velocity.y / Game.ticksPerSecond) /
				// deltaY;

				// System.out.println("DeltaY: " + Math.abs(deltaY) + " DeltaX:
				// " + Math.abs(deltaX));

				if (Math.abs(deltaY) <= Math.abs(deltaX)) {
					if (!o1.unmovable && !o2.unmovable) {
						o1.y -= deltaY * 0.5;
						o2.y += deltaY * 0.5;
					} else {
						if (o1.unmovable)
							o2.y += deltaY;
						if (o2.unmovable)
							o1.y -= deltaY;
					}

					if (!o1.unmovable)
						o1.x += (o2.velocity.x / Game.ticksPerSecond); // Moving
																		// platforms
					if (!o1.unmovable) {
						o1.velocity.y = (deltaY == top) ? Math.max(-o1.velocity.y * o1.elasticity, o1.velocity.y)
								: Math.min(-o1.velocity.y * o1.elasticity, o1.velocity.y);
					}
					if (!o2.unmovable) {
						o2.velocity.y = (deltaY == top) ? Math.max(-o2.velocity.y * o2.elasticity, o2.velocity.y)
								: Math.min(-o2.velocity.y * o1.elasticity, o2.velocity.y);
					}

					if (deltaY == top) {
						o2.touching[0] = true;
						o1.touching[1] = true;
					} else {
						o1.touching[0] = true;
						o2.touching[1] = true;
					}
				} else {
					if (!o1.unmovable && !o2.unmovable) {
						o1.x -= deltaX * 0.5;
						o2.x += deltaX * 0.5;
					} else {
						if (o1.unmovable)
							o2.x += deltaX;
						if (o2.unmovable)
							o1.x -= deltaX;
					}

					if (!o1.unmovable)
						o1.velocity.x = (deltaX == left) ? Math.max(-o1.velocity.x * o1.elasticity, o1.velocity.x)
								: Math.min(-o1.velocity.x * o1.elasticity, o1.velocity.x);
					if (!o2.unmovable)
						o2.velocity.x = (deltaX == left) ? Math.max(-o2.velocity.x * o2.elasticity, o2.velocity.x)
								: Math.min(-o2.velocity.x * o2.elasticity, o2.velocity.x);

					if (deltaX == left) {
						o2.touching[2] = true;
						o1.touching[3] = true;
					} else {
						o1.touching[2] = true;
						o2.touching[3] = true;
					}

				}
				return true;
			}
		}
		return false;
	}
}
