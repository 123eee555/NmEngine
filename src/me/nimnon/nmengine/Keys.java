package me.nimnon.nmengine;

public class Keys {
	
	public boolean[] keys;
	public boolean[] keysJustPressed;
	
	public Keys() {
		keys = new boolean[1024];
		keysJustPressed = new boolean[1024];
	}
	
	/**
	 * Returns true if key is pressed down.
	 * 
	 * @param charCode
	 *            Ascii keycode to check
	 * @return if character is down
	 */
	public boolean getKeyPressed(int charCode) {
		if (keys[charCode])
			return true;
		else
			return false;
	}
	
	/**
	 * Returns true if key was just pressed.
	 * 
	 * @param charCode
	 *            Ascii keycode to check
	 * @return if character was just pressed
	 */
	public boolean getKeyJustPressed(int charCode) {
		if (keysJustPressed[charCode]) {
			keysJustPressed[charCode] = false;
			return true;
		} else {
			return false;
		}
	}
}
