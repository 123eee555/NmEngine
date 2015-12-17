package me.nimnon.nmengine.entity.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.lang.reflect.Method;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.core.Camera;
import me.nimnon.nmengine.entity.GameObject;
public abstract class Button extends GameObject {

	public boolean mouseOn = false;
	public boolean pressed = false;
	public boolean justPressed = false;
	
	public Font font = Game.fonts.boocity;
	public Color color = Color.getHSBColor((float)Math.random(), 0.5f, 0.9f);
	public String text = "";

	public Method onPressMethod;

	/**
	 * Creates a blank 128x32 button at position
	 * @param x Position on x axis
	 * @param y Position on y axis
	 */
	public Button(double x, double y) {
		super(x, y);
		this.width = 128;
		this.height = 32;
	}

	/**
	 * Creates a button with specified text and dimension
	 * @param text Display text
	 * @param x Position on x axis
	 * @param y Position on y axis
	 * @param width Width of object
	 * @param height Height of object
	 */
	public Button(String text, double x, double y, double width, double height) {
		super(x, y, width, height);
		this.text = text;
	}

	/**
	 * Create a button object with this text and dimension
	 * @param text Display text
	 * @param rect Dimensions
	 */
	public Button(String text, Rectangle rect) {
		super(rect);
		this.text = text;
	}

	public void create() {
		
	}

	public void update() {
		super.update();
		mouseOn = checkForMouse();
		if (!pressed && Game.mouse.mouse1Down && mouseOn) {
			justPressed = true;

		}
		pressed = (Game.mouse.mouse1Down && mouseOn) ? true : false;
		if (!pressed && justPressed && mouseOn) {
			onPress();
			justPressed = false;

		}
		if (!mouseOn) {
			justPressed = false;
		}

	}

	public void draw() {
		for (int i = 0; i < Game.cameras.size(); i++) {
			if (Game.cameras.get(i).isOnScreen(this)) {
				Camera cam = Game.cameras.get(i);
				cam.imageGraphics.setColor(color);
				if (justPressed) {
					cam.imageGraphics.setColor(color.darker());
				}
				cam.imageGraphics.fillRect((int) (x - (cam.x * paralax.x)), (int) (y - (cam.y * paralax.y)), (int) width - 1, (int) height - 1);
				cam.imageGraphics.setColor(Color.white);
				cam.imageGraphics.setFont(font.deriveFont(Font.PLAIN,(float)(height/1.5f)));
				cam.imageGraphics.drawString(text, (int)(x-(cam.x * paralax.x)+5), (int)(y-(cam.y * paralax.y)+(float)(height/1.5f)));
			}
		}
	}

	/**
	 * Because java does not support defining callback functions through
	 * parameters you must define the onPress action during instantiation, to
	 * get around this you must define your fancy fancy onClick logic around
	 * these limits! Sorry!
	 */

	public abstract void onPress();

	/**
	 * Returns true if the mouse is over the button
	 * @return boolean Is the mouse over the button?
	 */
	public boolean checkForMouse() {
		Boolean hits = false;
		Camera cam = Game.activeCamera;
		if ((Game.mouse.x + (cam.x* paralax.x))  > this.x && (Game.mouse.x + (cam.x* paralax.x)) < this.x + this.width - 1) {
			if ((Game.mouse.y + (cam.y * paralax.y))  > this.y && (Game.mouse.y + (cam.y * paralax.y)) < this.y + this.height - 1) {
				hits = true;
			}
		}
		return hits;
	}
}
