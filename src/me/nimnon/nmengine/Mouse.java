package me.nimnon.nmengine;

public class Mouse {

	/**
	 * Cursor position on the screen relative to the JPanel Component
	 */
	private double x, y = 0d;

	/**
	 * Is respective mouse button down?
	 */
	private boolean mouse1Down, mouse2Down, mouse3Down = false;

	private boolean mouse1JustPressed, mouse2JustPressed, mouse3JustPressed = false;

	public Mouse() {
		
	}

	public boolean getMouse1JustPressed() {
		if (mouse1JustPressed) {
			mouse1JustPressed = false;
			return true;
		} else
			return false;
	}

	public void setMouse1JustPressed(boolean value) {
		mouse1JustPressed = value;
	}

	public boolean getMouse2JustPressed() {
		if (mouse2JustPressed) {
			mouse2JustPressed = false;
			return true;
		} else
			return false;
	}
	
	public void setMouse2JustPressed(boolean value) {
		mouse2JustPressed = value;
	}

	public boolean getMouse3JustPressed() {
		if (mouse3JustPressed) {
			mouse3JustPressed = false;
			return true;
		} else
			return false;
	}
	
	public void setMouse3JustPressed(boolean value) {
		mouse3JustPressed = value;
	}

	public boolean getMouse1Down() {
		return mouse1Down;
	}

	public void setMouse1Down(boolean mouse1Down) {
		this.mouse1Down = mouse1Down;
	}

	public boolean getMouse2Down() {
		return mouse2Down;
	}

	public void setMouse2Down(boolean mouse2Down) {
		this.mouse2Down = mouse2Down;
	}

	public boolean getMouse3Down() {
		return mouse3Down;
	}

	public void setMouse3Down(boolean mouse3Down) {
		this.mouse3Down = mouse3Down;
	}

	public double getxWorld() {
		return (x/ Game.activeCamera.zoom) + (Game.activeCamera.x);
	}

	public double getyWorld() {
		return (y/ Game.activeCamera.zoom) + (Game.activeCamera.y);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
