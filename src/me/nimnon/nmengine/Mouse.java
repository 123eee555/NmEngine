package me.nimnon.nmengine;

public class Mouse{

	/**
	 * Cursor position on the screen relative to the JPanel Component
	 */
	public double x, y = 0d;
	
	/**
	 * Cursor position in the world from the reference of the main camera
	 */
	public double xWorld, yWorld = 0d;

	/** 
	 * Is respective mouse button down?
	 */
	public boolean mouse1Down, mouse2Down, mouse3Down = false;
	
	public Mouse() {
		// TODO Auto-generated constructor stub
	}
	
}
