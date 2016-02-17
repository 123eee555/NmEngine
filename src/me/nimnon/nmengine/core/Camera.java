package me.nimnon.nmengine.core;

import java.awt.Graphics2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.entity.Basic;
import me.nimnon.nmengine.entity.GameObject;
import me.nimnon.nmengine.entity.Sprite;
import me.nimnon.nmengine.entity.tile.TileMap;

/**
 * Camera class, renders slice of the world specified at x and y
 * 
 * @author Nimnon
 *
 */
public class Camera {

	/**
	 * Screen X
	 */
	public double screenx = 0d;

	/**
	 * Screen Y
	 */
	public double screeny = 0d;

	/**
	 * World X
	 */
	public double x = 0d;

	/**
	 * World Y
	 */
	public double y = 0d;
	
	public double offsetX = 0d;
	
	public double offsetY = 0d;

	/**
	 * Camera screen-space width in pixels, width in worldspace is width/zoom
	 */
	public double width = 400d;

	/**
	 * Camera screen-space height in pixels, width in worldspace is height/zoom
	 */
	public double height = 300d;

	/**
	 * World zoom
	 */
	public double zoom = 2;

	/**
	 * Follow target
	 */
	private GameObject target;
	
	private Double offset = new Double(0, 0);

	/**
	 * Follow smoothness
	 */
	private double lerp = 1.0d;

	private double shakeX = 0;
	
	private double shakeY = 0;
	
	private double shakeDecay = 1;
	/**
	 * Image drawn to the main JPanel
	 */
	public BufferedImage imageData;

	/**
	 * Graphics object used to draw to this camera
	 */
	public Graphics2D imageGraphics;

	private double lastHeight, lastWidth;

	/**
	 * Sets up a plain camera with default properties
	 */
	public Camera() {
		update();
	}

	/**
	 * Sets up a camera
	 * 
	 * @param x
	 *            - Screen-space y
	 * @param y
	 *            - Screen-space y
	 * @param width
	 *            - Screen-space width
	 * @param height
	 *            - Screen-space height
	 * @param zoom
	 *            - Zoom amount
	 */
	public Camera(double x, double y, double width, double height, double zoom) {
		this.screenx = x;
		this.screeny = y;
		this.width = width;
		this.height = height;
		this.zoom = zoom;
		update();
	}

	/**
	 * Called every frame
	 */
	public void update() {

		if (target != null) {
			x -= ((int)((x)+((width/zoom)/2))-(int)target.getCenter().x - offset.x)/(Game.ticksPerSecond/lerp);
			y -= ((int)((y)+((height/zoom)/2))-(int)target.getCenter().y - offset.y)/(Game.ticksPerSecond/lerp);
		}
		
		offsetX = Math.random()*(shakeX*2)-(shakeX);
		offsetY = Math.random()*(shakeY*2)-(shakeY);
		
		shakeX = Math.max(0, shakeX - shakeDecay);
		shakeY = Math.max(0, shakeY - shakeDecay);

		if (width != lastWidth || height != lastHeight) {
			imageData = new BufferedImage((int) (width / zoom), (int) (height / zoom), BufferedImage.TYPE_INT_ARGB);
			imageGraphics = imageData.createGraphics();
			
		}

		lastWidth = width;
		lastHeight = height;
	}

	/**
	 * Called every render by the gameThread
	 * 
	 * @param g2d
	 *            Graphics to draw to
	 */
	public void draw(Graphics2D g2d) {
		
		g2d.drawImage(imageData, (int) screenx, (int) screeny, (int) width, (int) height, null);

		imageGraphics.setColor(Game.backgroundColor);
		imageGraphics.fillRect(0, 0, imageData.getWidth(), imageData.getHeight());

	}

	/**
	 * Sets this camera to folow specified object, higher lerp makes the
	 * folowing more smooth. lerp can be one at the lowest
	 * 
	 * @param object
	 *            Object to folow
	 * @param lerp
	 *            Lerp value
	 */
	public void folow(GameObject object, double lerp) {
		this.target = object;
		if (lerp >= 1)
			this.lerp = lerp;
		else
			this.lerp = 1;
	}

	public void stopFolowing() {
		target = null;
		lerp = 1;
	}
	
	public void setOffset(double x, double y) {
		offset.setLocation(x, y);
	}
	
	public void shakeScreen(int xShake, int yShake, int decayRate) {
		shakeX = xShake;
		shakeY = yShake;
		shakeDecay = decayRate;
	}

	/**
	 * Check if object is on screen
	 * 
	 * @param o1
	 *            Object to check
	 * @return boolean Is the object on screen?
	 */
	public boolean isOnScreen(Basic o1) {
		if (o1 instanceof Sprite) {
			Sprite object = (Sprite) o1;
			if ((((object.x * object.paralax.x) - object.offset.x)+object.getSpriteWidth() > x)
					&& (((object.x * object.paralax.x) - object.offset.x) < x + (width/zoom))
					&& (((object.y * object.paralax.y) - object.offset.y)+object.getSpriteHeight() > y)
					&& (((object.y * object.paralax.y) - object.offset.y) < y + (height/zoom))) {
				return true;
			}

		} else if (o1 instanceof GameObject) {
			GameObject object = (GameObject) o1;
			if ((object.x) + (object.width) >= x * object.paralax.x && (object.x) <= (x * object.paralax.x) + (width / zoom)) {
				if ((object.y) + (object.height) >= y * object.paralax.y && (object.y) <= (y * object.paralax.y) + (height / zoom)) {
					return true;
				}
			}
		} else if (o1 instanceof TileMap) {
			TileMap object = (TileMap) o1;
			if (object.getWidthInTiles() * object.getTileWidth() >= x && 0 <= x + (width / zoom)) {
				if (object.getHeightInTiles() * object.getTileHeight() >= y && 0 <= y + (height / zoom)) {
					return true;
				}
			}
		} else {
			System.out.println("Unable to draw: " + o1.getClass().getName());
		}
		return false;
	}

}
