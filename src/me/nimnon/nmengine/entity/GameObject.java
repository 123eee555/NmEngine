package me.nimnon.nmengine.entity;

import java.awt.Rectangle;
import java.awt.geom.Point2D;

import me.nimnon.nmengine.Game;

/**
 * Game object class, skeleton for the sprite class that handles motion and
 * dimension
 * 
 * @author Nimnon
 *
 */
public class GameObject extends Basic {

	/**
	 * Position on respective axis
	 */
	public double x, y = 0d;

	/**
	 * Whether or not the GameObject is movable by the physics system (use for platforms)
	 */
	public boolean movable = true;

	/**
	 * Position paralax of this object in relation to cameras
	 */
	public Point2D.Double paralax = new Point2D.Double(1, 1);

	/**
	 * Size on respective axis
	 */
	public double width, height = 16d;

	/**
	 * Where this object was last frame
	 */
	public Point2D.Double last = new Point2D.Double(0, 0);

	/**
	 * Where this object might be next frame
	 */
	public Point2D.Double next = new Point2D.Double(0, 0);

	/**
	 * Maximum velocity.
	 */
	public Point2D.Double maxVelocity = new Point2D.Double(0, 0);

	/**
	 * Current velocity in pixels per second
	 */
	public Point2D.Double velocity = new Point2D.Double(0, 0);

	/**
	 * Current acceleration in pixels per second
	 */
	public Point2D.Double acceleration = new Point2D.Double(0, 0);

	/**
	 * Drag on each axis
	 */
	public Point2D.Double drag = new Point2D.Double(1, 1);

	public double elasticity = 0;

	/**
	 * Center of the Object
	 */
	private Point2D.Double center = new Point2D.Double(x + (width / 2), y + (height / 2));

	/**
	 * Sides that are currently contacting another solid GameObject
	 */
	public boolean[] touching = new boolean[4];

	/**
	 * Basic object class that handles sprite movement
	 */
	public GameObject() {

	}

	/**
	 * Basic object class that handles sprite movement
	 * 
	 * @param x
	 *            Position on x axis
	 * @param y
	 *            Position on y axis
	 */
	public GameObject(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Basic object class that handles sprite movement
	 * 
	 * @param x
	 *            Position on x axis
	 * @param y
	 *            Position on y axis
	 * @param width
	 *            Object width
	 * @param height
	 *            Object height
	 */
	public GameObject(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Basic object class that handles sprite movement
	 * 
	 * @param rect
	 *            Rectangle to define dimensions and location
	 */
	public GameObject(Rectangle rect) {
		x = rect.x;
		y = rect.y;
		width = rect.width;
		height = rect.height;
	}

	/**
	 * Gets Center of object as Point2D
	 * 
	 * @return center
	 */
	public Point2D.Double getCenter() {
		center.setLocation(this.x + (width / 2), this.y + (height / 2));
		return center;
	}
	
	public Point2D.Double getMax() {
		center.setLocation(this.x + (width), this.y + (height));
		return center;
	}

	public void update() {
		
	}

	public void preUpdate() {
		updateMotion();
	}

	public void postUpdate() {

		touching[0] = false;
		touching[1] = false;
		touching[2] = false;
		touching[3] = false;
	}

	/**
	 * Updates the position and motion of this gameObject
	 */
	private void updateMotion() {
		last.setLocation(x, y);
		next.setLocation(x + (velocity.x / Game.ticksPerSecond), y + (velocity.y / Game.ticksPerSecond));

		velocity.x += acceleration.x / Game.ticksPerSecond;
		velocity.y += acceleration.y / Game.ticksPerSecond;

		if (maxVelocity.y != 0)
			velocity.y = Math.max(Math.min(velocity.y, maxVelocity.y), -maxVelocity.y);
		if (maxVelocity.x != 0)
			velocity.x = Math.max(Math.min(velocity.x, maxVelocity.x), -maxVelocity.x);

		x += velocity.x / Game.ticksPerSecond;
		y += velocity.y / Game.ticksPerSecond;

		if (velocity.x > 0) {
			velocity.x -= drag.x / Game.ticksPerSecond;
		} else {
			velocity.x += drag.x / Game.ticksPerSecond;
		}
		if (velocity.y > 0) {
			velocity.y -= drag.y / Game.ticksPerSecond;
		} else {
			velocity.y += drag.y / Game.ticksPerSecond;
		}
		if (Math.abs(velocity.x) <= drag.x / Game.ticksPerSecond && acceleration.x == 0) {
			velocity.x = 0;
		}

		if (Math.abs(velocity.y) <= drag.y / Game.ticksPerSecond && acceleration.y == 0) {
			velocity.y = 0;
		}
	}

	public void draw() {

	}

	@Override
	public void create() {
		this.x = x + 0.001;
		this.y = y + 0.001;
	}

	@Override
	public void destroy() {
		this.parent.getChildren().remove(this);
	}

}
