package me.nimnon.nmengine.entity;

import java.util.ArrayList;

/**
 * Group class, holds a group of Basics, useful for layering
 * @author Nimnon
 *
 */
public class Group implements Basic {

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
			children.get(i).preUpdate();
			children.get(i).update();
			children.get(i).postUpdate();
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
	 * @param object Object to add
	 */
	public void add(Basic object) {
		children.add(object);
		object.create();
	}
	
	/**
	 * Removes object from group
	 * @param object Object to remove
	 */
	public void remove(Basic object) {
		children.remove(object);
		object.destroy();
	}

	public void preUpdate() {
		// TODO Auto-generated method stub
		
	}

	public void postUpdate() {
		// TODO Auto-generated method stub
		
	}

	public void create() {
		// TODO Auto-generated method stub
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
