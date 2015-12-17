package me.nimnon.nmengine.entity;

/**
 * Basic root class, all objects inherit this class
 * 
 * @author Nimnon
 *
 */
public interface Basic {

	/**
	 * Called before update()
	 */
	public void preUpdate();

	/**
	 * Called when the State updates
	 */
	public abstract void update();

	/**
	 * Called after update()
	 */
	public void postUpdate();
	
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
	public void destroy();

}
