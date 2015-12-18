package me.nimnon.nmengine.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.core.Animation;
import me.nimnon.nmengine.core.Camera;

/**
 * Game object with a graphic, graphics must be loaded or created with
 * loadGraphic() or makeGraphic().
 * 
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
	public Color color = Color.getHSBColor(new Random().nextFloat(), 0.6f, 0.9f);

	/**
	 * List of animations loaded by the sprite
	 */
	public ArrayList<Animation> anims = new ArrayList<Animation>();

	/**
	 * Current animation
	 */
	public Animation curAnim = new Animation();

	/**
	 * Delay between frame change
	 */
	private double frameTimer;

	/**
	 * Current frame
	 */
	public int frame = 0;

	/**
	 * used by the animation logic
	 */
	public int index = 0;

	/**
	 * Width and Height of the image
	 */
	public int imageWidth, imageHeight;

	/**
	 * Flip the graphic on the respective axis?
	 */
	public boolean flipX,flipY = false;
	
	
	/**
	 * Offset the sprite by this number of pixels on specified axis
	 */
	public int offsetX,offsetY = 0;

	/**
	 * Creates basic instance of a Sprite, uses the default graphic
	 */
	public Sprite() {
		super();
		// makeGraphic((int)width,(int)height,color);
		setGraphic("default.png");
	}

	/**
	 * Creates basic instance of a Sprite at position x and y, uses the default
	 * graphic
	 * 
	 * @param x Position on x axis
	 * @param y Position on y axis
	 */
	public Sprite(double x, double y) {
		super(x, y);
		setGraphic("../NmEngine/assets/default.png");
	}

	/**
	 * Creates basic instance of a Sprite at position x and y, creates graphic
	 * to size using a random color
	 * 
	 * @param x Position on x axis
	 * @param y Position on y axis
	 * @param width Width of object
	 * @param height Height of object
	 */
	public Sprite(double x, double y, double width, double height) {
		super(x, y, width, height);
		makeGraphic();
	}

	/**
	 * Creates basic instance of a Sprite with the dimensions of the supplied
	 * Rect, creates graphic to size using a random color
	 * 
	 * @param rect Dimensions to use
	 */
	public Sprite(Rectangle rect) {
		super(rect);
		makeGraphic((int) rect.width, (int) rect.height, color);
	}

	/**
	 * Creates a rectangle for a graphic
	 * 
	 * @param width Width of graphic
	 * @param height Height of graphic
	 * @param color Graphic color
	 */
	public void makeGraphic(int width, int height, Color color) {
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(color);
		g2d.fillRect(0, 0, width, height);
		this.width = width;
		this.height = height;
		imageWidth = (int) width;
		imageHeight = (int) height;
	}

	/**
	 * Creates graphic to width and height automatically
	 * 
	 * @param color Graphic color
	 */
	public void makeGraphic(Color color) {
		image = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(color);
		g2d.fillRect(0, 0, (int) width, (int) height);
		imageWidth = (int) width;
		imageHeight = (int) height;
	}

	/**
	 * Creates graphic to width and height automatically with a random color
	 */
	public void makeGraphic() {
		image = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = image.createGraphics();
		g2d.setColor(color);
		g2d.fillRect(0, 0, (int) width, (int) height);
		imageWidth = (int) width;
		imageHeight = (int) height;
	}

	/**
	 * Sets graphic to image at path, image must be located in the assets folder
	 * 
	 * @param path
	 *            - Path to image
	 * @param animated
	 *            - Is the object animated (Is it a spritesheet?)?
	 * @param width
	 *            - Width of sprite (Used for splitting the spritesheet)
	 * @param height
	 *            - Height of sprite (Used for splitting the spritesheet)
	 */
	public void setGraphic(String path, boolean animated, int width, int height) {
		File img = new File(path);
		try {
			image = ImageIO.read(img);
			this.width = width;
			this.height = height;
			imageWidth = width;
			imageHeight = height;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sets graphic to image at path, image must be located in the assets folder
	 * 
	 * @param path
	 *            - Path to image
	 */
	public void setGraphic(String path) {
		File img = new File(path);
		try {
			image = ImageIO.read(img);
			this.width = image.getWidth();
			this.height = image.getHeight();
			imageWidth = image.getWidth();
			imageHeight = image.getHeight();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Add new animation
	 * @param name Animation name
	 * @param frames Frray of frames
	 * @param framerate Framerate in Frames per second
	 * @param loops Does the animation loop?
	 */
	public void addAnim(String name, int[] frames, int framerate, boolean loops) {
		anims.add(new Animation(name, framerate, frames, loops));
	}

	/**
	 * Play animation
	 * @param name Animation to play
	 */
	public void playAnim(String name) {
		if (name != curAnim.name) {
			for (int i = 0; i < anims.size(); i++) {
				if (anims.get(i).name == name) {
					curAnim = anims.get(i);
					index = curAnim.frames[0];
				}
			}
		}
	}

	/**
	 * Draw sprite to cameras it appears on
	 */
	public void draw() {
		for (int i = 0; i < Game.cameras.size(); i++) {
			if (Game.cameras.get(i).isOnScreen(this)) {
				Camera cam = Game.cameras.get(i);

				BufferedImage image2 = image.getSubimage((int) ((index * imageWidth) % image.getWidth()),
						(int) (Math.floor(index / (image.getWidth() / imageWidth)) * imageHeight),
						(int) imageWidth,
						(int) imageHeight);
				
				if(flipX) {
					AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
					tx.translate(-imageWidth, 0);
					AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
					image2 = op.filter(image2, null);
				}
				if(flipY) {
					AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
					tx.translate(0, -imageHeight);
					AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
					image2 = op.filter(image2, null);
				}
				
				cam.imageGraphics.drawImage(image2, (int)x-(int)(Game.cameras.get(i).x*paralax.x)-(int)offsetX, (int)y-(int)(Game.cameras.get(i).y*paralax.y)-(int)offsetY, null);

				if (drawDebug) {
					cam.imageGraphics.setColor(color);
					cam.imageGraphics.drawRect((int)x-(int)(Game.cameras.get(i).x*paralax.x), (int)y-(int)(Game.cameras.get(i).y * paralax.y), (int)(width - 1), (int)(height - 1));
				}
			}
		}

	}

	/**
	 * Called every frame
	 */
	public void update() {
		super.update();
		updateAnim();
	}

	/**
	 * Updates the animation if one exists, called every tick
	 */
	public void updateAnim() {
		if (curAnim != null) {
			frameTimer += Game.elapsedTime;
			while (frameTimer > curAnim.delay && curAnim.delay != 0) {
				frameTimer -= curAnim.delay;
				if (frame == curAnim.frames.length - 1) {
					if (curAnim.loops) {
						frame = 0;
					}
				} else
					frame++;
				index = curAnim.frames[frame];
			}
		}
	}

}
