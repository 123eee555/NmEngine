package me.nimnon.nmengine.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Group class, holds a group of Basics, useful for layering
 * 
 * @author Nimnon
 *
 */
public class Group extends Basic {

	/**
	 * Children Array
	 */
	private ArrayList<Basic> children = new ArrayList<Basic>();

	/**
	 * Sort this array by children's y axis? Useful for top-down games
	 */
	public boolean sortByY = false;

	/**
	 * Creates an empty group
	 */
	public Group() {

	}

	/**
	 * Called every update
	 */
	public void update() {
		for (int i = 0; i < children.size(); i++) {
			try {
				children.get(i).preUpdate();
				children.get(i).update();
				children.get(i).postUpdate();
			} catch (NullPointerException | IndexOutOfBoundsException e) {
				// This happens when the children are cleared during an update,
				// break the loop
				break;
			}

		}
		if (sortByY)
			sortByPosY();

	}

	/**
	 * Called every render
	 */
	public void draw() {
		for (int i = 0; i < children.size(); i++) {
			try {
				children.get(i).draw();
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}
	}

	/**
	 * Adds object to the group
	 * 
	 * @param object
	 *            Object to add
	 */
	public void add(Basic object) {
		object.parent = this;
		object.create();
		children.add(object);
	}

	/**
	 * Removes object from group
	 * 
	 * @param object
	 *            Object to remove
	 */
	public void remove(Basic object) {
		children.remove(object);
		object.destroy();
	}

	public ArrayList<Basic> getChildren() {
		return children;
	}

	public void sortByPosY() {
		try {
			Collections.sort(children, new Comparator<Basic>() {
				public int compare(Basic o1b, Basic o2b) {
					if (o1b instanceof GameObject && o2b instanceof GameObject) {
						GameObject o1 = (GameObject) o1b;
						GameObject o2 = (GameObject) o2b;
						if (o1.y == o2.y) {
							return 0;
						}
						return o1.y < o2.y ? -1 : 1;
					}
					return 0;

				}
			});
		} catch (IndexOutOfBoundsException | NullPointerException e) {
			e.printStackTrace();
		}

	}

	public void preUpdate() {

	}

	public void postUpdate() {

	}

	public void create() {

	}

	public void destroy() {
		this.children.clear();
		this.children = null;
	}

}
