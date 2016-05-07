package me.nimnon.nmengine.entity;

import me.nimnon.nmengine.util.Holder;

/**
 * Basic root class, all objects inherit this class
 * 
 * @author Nimnon
 *
 */
public abstract class Basic {
	
	/**
	 * Whatever group this basic is a member of, game states have no parent
	 */
	public Holder<?> parent;
	
	/**
	 * Called before update()
	 */
	public abstract void preUpdate();

	/**
	 * Called when the State updates
	 */
	public abstract void update();

	/**
	 * Called after update()
	 */
	public abstract void postUpdate();
	
	/**
	 * Called when the object is added to a State or Group
	 */
	public abstract void create();
	/**
	 * Called when the State is rendering
	 */
	public abstract void draw();

	/**
	 * Destroys object, removing it from existence, objects should be dereferenced after
	 * calling destroy, as many parts of the object are dereferenced for the garbage collector.
	 * If you simply want to hide an object, move it out of its group until I implement some
	 * sort of kill method, lol.
	 */
	public void destroy() {
		parent.getChildren().remove(this);
		parent.getChildren().trimToSize();
	}

}
