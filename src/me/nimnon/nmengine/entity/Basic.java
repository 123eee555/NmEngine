package me.nimnon.nmengine.entity;

import java.awt.Graphics2D;


/**
 * Very, VERY basic gameobject class, pretty much only exists to be extended
 * @author Nimnon
 *
 */
public abstract class Basic {

	/**
	 * Constructor for the basic object
	 */
	public Basic() {
		
	}
	/**
	 * Called every tick, a ticks per second defined on Game instantiation.
	 */
	public abstract void update();
	/**
	 * Method used by the parent state to draw the class, don't call this, it wont work, but you can override to draw
	 * Shapes n' stuff.
	 * @param g2d Graphics object provided by the state
	 */
	public abstract void draw(Graphics2D g2d);

}
