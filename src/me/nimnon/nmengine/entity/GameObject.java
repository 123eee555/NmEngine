package me.nimnon.nmengine.entity;

import java.awt.geom.Point2D;

import me.nimnon.nmengine.util.Rect;

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
	 * Drag on each axis
	 */
	public Point2D.Double drag = new Point2D.Double(0, 0);

	/**
	 * Center of the Object
	 */
	public Point2D.Double center = new Point2D.Double(x + (width / 2), y + (height / 2));

	/**
	 * Sides that are currently contacting another solid GameObject
	 */
	public boolean[] touching = new boolean[4];

	/**
	 * Basic object class that handles sprite movement
	 */
	public GameObject() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Basic object class that handles sprite movement
	 * 
	 * @param x
	 * @param y
	 */
	public GameObject(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Basic object class that handles sprite movement
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
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
	 *            - Rectangle to define dimensions and location
	 */
	public GameObject(Rect rect) {
		x = rect.x;
		y = rect.y;
		width = rect.width;
		height = rect.height;
	}

	/**
	 * Called every update, moves object and updates center
	 */
	public void update() {
		center.setLocation(this.x + (width / 2), this.y + (height / 2));
	}

	public void draw() {
	}

}
