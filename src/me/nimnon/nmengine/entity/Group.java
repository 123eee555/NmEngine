package me.nimnon.nmengine.entity;

import java.awt.Graphics2D;
import java.util.ArrayList;

import me.nimnon.nmengine.Game;

public class Group extends Basic{

	private ArrayList<Basic> children;

	public Group() {
		children = new ArrayList<>();
	}

	/**
	 * Called every tick.
	 */
	public void update() {
		for (int i = 0; i < children.size(); i++) {
			children.get(i).preUpdate();
			children.get(i).update();
			children.get(i).postUpdate();
		}
	}

	/**
	 * Called as many times as the computer can per second, draws every object
	 * that is a child of the group. Draw order based on order added to stage
	 */
	public void draw(Graphics2D g2d) {
		for (int i = 0; i < children.size(); i++) {
			children.get(i).draw(g2d);
		}
	}

	/**
	 * Adds an object to the group
	 * 
	 * @param object
	 *            Object to add to stage, must extend the Basic class.
	 */
	public void add(Basic object) {
		object.create();
		children.add(object);
	}

	/**
	 * Destroys the group, sets the children array to null and the java garbage
	 * collector takes care of the rest
	 */
	public void destroy() {
		children = null;
	}
	
	public void collide(Basic object) {
		for (int i = 0; i < children.size(); i++) {
			Game.collide(object, children.get(i));
		}
	}

}
