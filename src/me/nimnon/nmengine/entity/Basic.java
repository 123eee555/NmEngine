package me.nimnon.nmengine.entity;

import java.awt.Graphics2D;
import java.awt.Point;

import com.sun.javafx.geom.Point2D;

/**
 * Very, VERY basic object class, pretty much only exists to be extended
 * 
 * @author Nimnon
 *
 */
public class Basic {

	public float x = 0;
	public float y = 0;

	public int width = 32;
	public int height = 32;
	
	/**
	 * Units traveled per second
	 */
	public Point2D velocity = new Point2D(0, 0);
	
	/**
	 * Next predicted position on each axis
	 */
	public Point next = new Point(0,0);
	
	/**
	 * Last position on each axis
	 */
	public Point last = new Point(0,0);
	
	/**
	 * Center points
	 */
	public Point center = new Point((int)(x+(width/2)),(int)(y+(height/2)));

	/**
	 * Constructor for the basic object
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Basic(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Basic() {
		// TODO Auto-generated constructor stub
	}

	public void create() {

	}

	/**
	 * Called every tick, a ticks per second defined on Game instantiation.
	 */
	public void update() {
		center.setLocation((int)(x+(width/2)),(int)(y+(height/2)));
	}

	/**
	 * Called every tick after update.
	 */
	public void postUpdate() {

	}

	/**
	 * Called every tick before update.
	 */
	public void preUpdate() {

	}

	/**
	 * Method used by the parent state to draw the class, don't call this, it
	 * wont work, but you can override to draw Shapes n' stuff.
	 * 
	 * @param g2d
	 *            Graphics object provided by the state
	 */
	public void draw(Graphics2D g2d) {
	}

}
