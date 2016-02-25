package me.nimnon.nmengine.entity.ui;

import java.awt.Color;
import java.awt.image.BufferedImage;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.core.Camera;

public class Text extends UIBasic {

	private BufferedImage graphic;

	public double scale =1;
	
	private String text;

	private Color color = Color.white;
	private Color background = Color.black;

	public Text() {
		// TODO Auto-generated constructor stub
	}

	public Text(double x, double y) {
		super(x, y);
		setText("");
	}

	public Text(double x, double y, String text) {
		super(x, y);
		setText(text);
	}

	private void setTextGraphic() {
		graphic = Game.fonts.makeString(text, color, background);
		width = Game.fonts.getStringWidth(text)*scale;
		
		height = Game.fonts.getStringHeight(text, 0)*scale;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		if (!(this.text == text)) {
			this.text = text;
			setTextGraphic();
		}
	}

	public void draw() {
		super.draw();
		for (int i = 0; i < Game.cameras.size(); i++) {
			Camera cam = Game.cameras.get(i);
			if (cam.isOnScreen(this)) {
				cam.imageGraphics.drawImage(graphic, (int) (((int) (x) * paralax.x) - (int) (cam.x - cam.getOffsetX())),
						(int) (((int) (y) * paralax.y) - (int) (cam.y - cam.getOffsetY())),(int)width,(int)height, null);
			}
		}
	}

}
