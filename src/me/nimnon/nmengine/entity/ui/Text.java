package me.nimnon.nmengine.entity.ui;

import java.awt.Color;
import java.awt.Font;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.core.Camera;
import me.nimnon.nmengine.entity.Basic;
import me.nimnon.nmengine.entity.GameObject;

public class Text extends GameObject{

	/**
	 * Display text
	 */
	public String text = "text";
	/**
	 * Text scale
	 */
	public int scale = 16;
	
	/** 
	 * Text font
	 */
	public Font font = Game.fonts.yoster;
	
	/**
	 * Color of the text
	 */
	public Color color = Color.black;

	/**
	 * Creates a text object with the specified position, text, and scale
	 * @param x Position on x axis
	 * @param y Position on y axis
	 * @param text Display text
	 * @param scale Font Size
	 */
	public Text(double x, double y, String text, int scale) {
		this.x = x;
		this.y = y;
		this.width = (text.length())*(scale);
		this.height= text.split("\n").length*scale;
		this.text = text;
		this.scale = scale;
	}
	
	public void draw() {
		for (int i = 0; i < Game.cameras.size(); i++) {
			if (Game.cameras.get(i).isOnScreen(this)) {
				Camera cam = Game.cameras.get(i);
				cam.imageGraphics.setColor(color);
				cam.imageGraphics.setFont(font.deriveFont(Font.PLAIN,scale));
				cam.imageGraphics.drawString(text, (int)(x-(cam.x*paralax.x)), (int)(y-(cam.y*paralax.y)+scale));
			}
		}
	}

}
