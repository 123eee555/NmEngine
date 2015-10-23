package me.nimnon.nmengine.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.util.Rect;

/**
 * Game object with a graphic, graphics must be loaded or created with
 * loadGraphic() or makeGraphic().
 * @author Nimnon
 *
 */
public class Sprite extends GameObject {

	/**
	 * If true the objects bounding box is drawn
	 */
	public boolean drawDebug = false;

	/**
	 * Image data of the object.
	 */
	private BufferedImage image;

	/**
	 * Object color, used in debug drawing
	 */
	public Color color = Color.getHSBColor(new Random().nextFloat(), new Random().nextFloat(), 0.9f);

	/**
	 * Creates basic instance of a Sprite, uses the default graphic
	 */
	public Sprite() {
		super();
		// makeGraphic((int)width,(int)height,color);
		setGraphic("default.png");
	}
	
	/**
	 * Creates basic instance of a Sprite at position x and y, uses the default graphic
	 * @param x
	 * @param y
	 */
	public Sprite(double x, double y) {
		super(x,y);
		setGraphic("default.png");
	}

	/**
	 * Creates basic instance of a Sprite at position x and y, creates graphic to size using a random color
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Sprite(double x, double y, double width, double height)
	{
		super(x, y, width, height);
		makeGraphic();
	}
	
	/**
	 * Creates basic instance of a Sprite with the dimensions of the supplied Rect, creates graphic to size using a random color
	 * @param rect
	 */
	public Sprite(Rect rect)
	{
		super(rect);
		makeGraphic((int)rect.width,(int)rect.height,color);
	}
	
	/**
	 * Creates a rectangle for a graphic
	 * @param width
	 * @param height
	 * @param color
	 */
	public void makeGraphic(int width, int height, Color color) {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(color);
		g2d.fillRect(0, 0, width, height);
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Creates graphic to width and height automatically
	 * @param color
	 */
	public void makeGraphic(Color color) {
		image = new BufferedImage((int)width, (int)height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(color);
		g2d.fillRect(0, 0, (int)width, (int)height);
	}
	
	/**
	 * Creates graphic to width and height automatically with a random color
	 */
	public void makeGraphic() {
		image = new BufferedImage((int)width, (int)height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(color);
		g2d.fillRect(0, 0, (int)width, (int)height);
	}

	/**
	 * Sets graphic to image at path, image must be located in the assets folder
	 * @param path - Path to image
	 * @param animated - Is the object animated (Is it a spritesheet?)?
	 * @param width - Width of sprite (Used for splitting the spritesheet)
	 * @param height - Height of sprite (Used for splitting the spritesheet)
	 */
	public void setGraphic(String path, boolean animated, int width, int height) {
		File img = new File("assets/" + path);
		try {
			image = ImageIO.read(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sets graphic to image at path, image must be located in the assets folder
	 * @param path - Path to image
	 */
	public void setGraphic(String path) {
		File img = new File("assets/" + path);
		try {
			image = ImageIO.read(img);
			this.width = image.getWidth();
			this.height = image.getHeight();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 
	 * Draw sprite to cameras it appears on
	 */
	public void draw() {
		for (int i = 0; i < Game.cameras.size(); i++) {
			if (Game.cameras.get(i).isOnScreen(this)) {
				Camera cam = Game.cameras.get(i);

				cam.imageGraphics.drawImage(image, (int) x, (int) y, null);

				if (drawDebug) {
					cam.imageGraphics.setColor(color);
					cam.imageGraphics.drawRect((int) (x - cam.x), (int) (y - cam.y), (int) width - 1, (int) height - 1);
				}
			}
		}
	}

}
