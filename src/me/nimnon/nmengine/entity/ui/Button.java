package me.nimnon.nmengine.entity.ui;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.core.Camera;
import me.nimnon.nmengine.util.ImageUtils;

public abstract class Button extends NineSlice {

	private boolean mouseOn = false;
	private boolean buttonDown = false;
	
	private Color color = Color.white;
	
	private Color oldColor = Color.white;
	
	private BufferedImage textGraphic;
	
	private String text = "Button";

	public Button(double x, double y) {
		super(x, y);

		this.width = 100;
		this.height = 20;
	}

	public Button(double x, double y, double width, double height) {
		super(x, y, width, height);
		
	}

	public Button(Rectangle rect) {
		super(rect);
		
	}

	public void create() {
		createNineSliceFromImage("/me/nimnon/nmengine/assets/buttonSlice.png");
		setText(text);
		
		setColor(Color.white);
		

	}
	
	public void setText(String text) {
		this.text = text;
		textGraphic = Game.fonts.makeString(text, Color.white, new Color(0, 0, 0, 0f));
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setColor(Color color) {
		this.color = color;
		updateColor(color);
	}
	
	public void updateColor(Color color) {
		for(int i = 0; i < sliceArray.length; i++) {
			ImageUtils.colorFilter(sliceArray[i], color, oldColor);
		}
		oldColor = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public abstract void onPress();

	public void update() {
		if (Game.mouse.getxWorld() > (int) x - (int) (Game.activeCamera.x * paralax.x)
				&& Game.mouse.getxWorld() < (int) x - (int) (Game.activeCamera.x * paralax.x) + width
				&& Game.mouse.getyWorld() > (int) y - (int) (Game.activeCamera.y * paralax.y)
				&& Game.mouse.getyWorld() < (int) y - (int) (Game.activeCamera.y * paralax.y) + height) {
			mouseOn = true;
		} else {
			mouseOn = false;
			buttonDown = false;
			
		}
		
		
		if (mouseOn && Game.mouse.getMouse1Down() && Game.mouse.getMouse1JustPressed()) {
			buttonDown = true;
			updateColor(this.color.darker());
		}
		
		if(buttonDown && !Game.mouse.getMouse1Down()){
			buttonDown = false;
			onPress();
			updateColor(this.color);
		}
	}

	public void draw() {
		super.draw();
		
		for (int i = 0; i < Game.cameras.size(); i++) {
			if (Game.cameras.get(i).isOnScreen(this)) {
				Camera cam = Game.cameras.get(i);
				
				cam.imageGraphics.drawImage(textGraphic, (int) ((x+(width/2))-(Game.fonts.getStringWidth(text)/2)) - (int) (Game.cameras.get(i).x * paralax.x),
						(int) ((y+(height/2))-4) - (int) (Game.cameras.get(i).y * paralax.y), null);
			}
		}
	}
}