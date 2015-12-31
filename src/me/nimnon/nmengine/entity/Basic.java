package me.nimnon.nmengine.entity;

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
	public Group parent;
	
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
	 * Called on creation
	 */
	public abstract void create();
	/**
	 * Called when the State is rendering
	 */
	public abstract void draw();

	/**
	 * Destroys object, currently non-functional
	 */
	public void destroy() {
		parent.getChildren().trimToSize();
	}

}
