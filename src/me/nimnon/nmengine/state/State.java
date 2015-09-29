package me.nimnon.nmengine.state;

import java.awt.Graphics2D;
import java.util.ArrayList;

import me.nimnon.nmengine.entity.Basic;

/**
 * State class, each of these are a scene that can be switched to and from.
 * @author Nimnon
 *
 */

public class State {

	private ArrayList<Basic> children;
	
	public State() {
		children = new ArrayList<>();
	}
	
	/**
	 * Called on state creation
	 */
	
	public void create() {
		
	}
	
	
	/**
	 * Called every tick, a ticks per second defined on Game instantiation.
	 */
	public void update() {
		for(int i = 0; i < children.size(); i++)
		{
			children.get(i).update();
		}
	}
	
	/**
	 * Called as many times as the computer can per second, draws every object that is a child of the state.
	 * Draw order based on order added to stage
	 */
	public void draw(Graphics2D g2d) {
		for(int i = 0; i < children.size(); i++)
		{
			children.get(i).draw(g2d);
		}
	}
	
	/**
	 * Adds an object to the state
	 * @param object
	 * 		Object to add to stage, must extend the Basic class.
	 */
	public void add(Basic object)
	{
		children.add(object);
	}
	
	/** 
	 * Destroys the state, sets the children array to null and the java garbage collector takes care of the rest
	 */
	public void destroy()
	{
		children = null;
	}

}
