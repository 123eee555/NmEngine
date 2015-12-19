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
		children.add(object);
		object.create();
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

	public void sortByPosY() {
		/*
		 * int highestY = Integer.MIN_VALUE; ArrayList<Basic> newChildren = new
		 * ArrayList<>(); for (int i = 0; i < children.size(); i++) { Basic
		 * child = children.get(i);
		 * 
		 * if (child instanceof GameObject) { GameObject gchild = (GameObject)
		 * child; if (gchild.y >= highestY) { newChildren.add(0, gchild);
		 * highestY = (int) gchild.y; System.out.println("Moved "
		 * +child.getClass().getSimpleName()+" to the top!"); } else {
		 * newChildren.add(gchild); System.out.println("Moved "
		 * +child.getClass().getSimpleName()+" to the bottom!"); }
		 * 
		 * } } System.out.println("--------------"); children = newChildren;
		 */
		try {
		Collections.sort(children, new Comparator<Basic>() {
			public int compare(Basic o1b, Basic o2b) {
				if (o1b instanceof GameObject && o2b instanceof GameObject) {
					GameObject o1 = (GameObject) o1b;
					GameObject o2 = (GameObject) o2b;
					if (o1.y == o2.y) {
						System.out.println("Returned 0:\n    " + o1b.getClass().getSimpleName() + " and " + o2b.getClass().getSimpleName());
						return 0;
					}
					return o1.y < o2.y ? -1 : 1;
				}
				System.out.println("Returned 0!!!@");
				return 0;

			}
		});
		} catch(IndexOutOfBoundsException | NullPointerException e) {
			//Donchu crash you group u
		}

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
		this.children.clear();
		this.children = null;
	}

}
