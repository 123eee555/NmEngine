package me.nimnon.nmengine.entity;

import java.awt.Graphics2D;


/**
 * Very, VERY basic object class, pretty much only exists to be extended
 * @author Nimnon
 *
 */
public class Basic {

	public float x=0;
	public float y=0;
	
	public int width=32;
	public int height=32;
	
	/**
	 * Constructor for the basic object
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Basic(int x, int y, int width, int height) {
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
	 * Method used by the parent state to draw the class, don't call this, it wont work, but you can override to draw
	 * Shapes n' stuff.
	 * @param g2d Graphics object provided by the state
	 */
	public void draw(Graphics2D g2d) {
	}

}
