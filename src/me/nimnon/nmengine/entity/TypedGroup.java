package me.nimnon.nmengine.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import me.nimnon.nmengine.util.Holder;

/**
 * Group class, holds a group of a predefined type, useful for layering
 * 
 * @author Nimnon
 *
 */
public class TypedGroup<T extends Basic> extends Basic implements Holder<T> {

	/**
	 * Children Array
	 */
	protected ArrayList<T> children = new ArrayList<>();

	/**
	 * Sort this array by children's y axis? Useful for top-down games
	 */
	public boolean sortByY = false;

	/**
	 * Creates an empty group
	 */
	public TypedGroup() {

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
				// This happens when a child clears the group, 
				// or a member of the group is removed before it is updated.
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
	public void add(T object) {
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
	public void remove(T object) {
		children.remove(object);
		object.destroy();
	}

	public ArrayList<T> getChildren() {
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
		super.destroy();
		this.children.clear();
		this.children = null;
	}

}
