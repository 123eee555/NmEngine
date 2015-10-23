package me.nimnon.nmengine.entity;

import java.util.ArrayList;

/**
 * Group class, holds a group of Basics, useful for layering
 * @author Nimnon
 *
 */
public class Group extends Basic {

	/**
	 * Children Array
	 */
	public ArrayList<Basic> children = new ArrayList<Basic>();

	/**
	 * Creates an empty group
	 */
	public Group() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Called every update
	 */
	public void update() {
		for (int i = 0; i < children.size(); i++) {
			children.get(i).update();
		}
	}

	/**
	 * Called every render
	 */
	public void draw() {
		for (int i = 0; i < children.size(); i++) {
			children.get(i).draw();
		}
	}
	
	/**
	 * Adds object to the group
	 * @param object
	 */
	public void add(Basic object) {
		children.add(object);
	}

}
