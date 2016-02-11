package me.nimnon.nmengine.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.core.Animation;
import me.nimnon.nmengine.core.Camera;
import me.nimnon.nmengine.util.ImageUtils;

public class Sprite extends GameObject {

	// Fields
	private BufferedImage graphic;
	private BufferedImage drawClip;

	private boolean animated;
	private ArrayList<Animation> anims = new ArrayList<>();
	private int animIndex = 0;
	private Animation currentAnimation;
	private double animTime = 0;
	private int sliceWidth;
	private int sliceHeight;

	public boolean scales = true;
	public boolean flipX;
	private boolean lastFlipX = false;
	public boolean flipY;
	private boolean lastFlipY = false;

	public Point offset = new Point(0, 0);

	// Constructors
	public Sprite() {
		super();
		makeGraphic(12, 12, Color.orange);
	}

	public Sprite(double x, double y) {
		super(x, y);
		makeGraphic(12, 12, Color.orange);
	}

	public Sprite(double x, double y, double width, double height) {
		super(x, y, width, height);
		makeGraphic((int) width, (int) width, Color.orange);
	}

	public Sprite(Rectangle rect) {
		super(rect);
		makeGraphic((int) rect.x, (int) rect.y, Color.orange);
	}

	// Graphic Methods
	public void makeGraphic(int width, int height, Color color) {
		this.animated = false;
		this.width = width;
		this.height = height;
		this.graphic = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.drawClip = graphic;
		Graphics2D g2d = this.graphic.createGraphics();

		g2d.setColor(color);
		g2d.fillRect(0, 0, (int) width, (int) height);
		g2d.dispose();
	}

	public void loadGraphic(String path) {
		this.animated = false;

		try {
			this.graphic = ImageUtils.getImage(path);
			this.drawClip = graphic;
			this.width = graphic.getWidth();
			this.height = graphic.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
			// Fall back to generated sprite..
			makeGraphic(12, 12, Color.orange);
		}

	}

	public void loadGraphic(BufferedImage image) {
		this.animated = false;

		this.graphic = image;
		this.drawClip = graphic;
		this.width = graphic.getWidth();
		this.height = graphic.getHeight();

	}

	public void loadGraphic(String path, int spriteWidth, int spriteHeight, boolean animate) {
		this.animated = false;

		try {
			this.graphic = ImageUtils.getImage(path);
		} catch (IOException e) {
			e.printStackTrace();
			// Fall back to generated sprite..
			makeGraphic(12, 12, Color.orange);
		} finally {
			this.animated = animate;
			this.width = spriteWidth;
			this.height = spriteHeight;
			this.sliceWidth = spriteWidth;
			this.sliceHeight = spriteHeight;
			this.drawClip = ImageUtils.getSlice(0, graphic, sliceWidth, sliceHeight);
		}

	}

	public void loadGraphic(BufferedImage image, int spriteWidth, int spriteHeight, boolean animate) {
		this.animated = false;

		this.graphic = image;
		this.animated = animate;
		this.width = spriteWidth;
		this.height = spriteHeight;
		this.sliceWidth = spriteWidth;
		this.sliceHeight = spriteHeight;
		this.drawClip = ImageUtils.getSlice(0, graphic, sliceWidth, sliceHeight);

	}

	public int getSpriteHeight() {
		if(!scales)
			return drawClip.getHeight();
		else
			return (int) width;
	}

	public int getSpriteWidth() {
		if(!scales)
			return drawClip.getWidth();
		else
			return (int) width;
	}

	// Animation
	public void addAnimation(String name, int[] frames, double framerate, boolean loops) {
		anims.add(new Animation(name, framerate, frames, loops));
	}

	public void playAnimation(String name) {
		for (int i = 0; i < this.anims.size(); i++) {
			if (this.anims.get(i).name.equals(name)) {
				if (this.currentAnimation != this.anims.get(i)) {
					this.currentAnimation = this.anims.get(i);
					this.animIndex = this.currentAnimation.frames[0];
				} else if (this.currentAnimation.loops == false && this.animIndex == currentAnimation.frames.length) {
					this.currentAnimation = this.anims.get(i);
					this.animIndex = this.currentAnimation.frames[0];
				}
			} else {
				System.out.println("No Animation found for name: " + name);
			}
		}
	}

	public int getCurrentFrame() {
		return animIndex;
	}

	public Animation getCurrentAnimation() {
		return currentAnimation;
	}

	public void updateAnimation() {
		int frame = animIndex;
		animTime += 1d / Game.ticksPerSecond;
		if (animTime > this.currentAnimation.delay) {

			if (this.currentAnimation.loops)
				this.animIndex = (this.animIndex + 1) % this.currentAnimation.frames.length;
			else if (this.animIndex < this.currentAnimation.frames.length)
				this.animIndex++;
			this.animTime = 0;
		}
		if (frame != animIndex) {
			drawClip = ImageUtils.getSlice(this.currentAnimation.frames[animIndex], graphic, sliceWidth, sliceHeight);
			if (flipX) {
				drawClip = ImageUtils.flipX(drawClip);
			}
			if (flipY) {
				drawClip = ImageUtils.flipY(drawClip);
			}
		}
	}

	// Updating and Drawing
	public void update() {
		super.update();

		if (this.animated && this.currentAnimation != null) {
			updateAnimation();
		}

	}

	public void draw() {
		super.draw();
		for (int i = 0; i < Game.cameras.size(); i++) {
			Camera cam = Game.cameras.get(i);
			if (cam.isOnScreen(this)) {

				if (flipX != lastFlipX) {
					drawClip = ImageUtils.flipX(drawClip);
				}
				if (flipY != lastFlipY) {
					drawClip = ImageUtils.flipY(drawClip);
				}
				lastFlipX = flipX;
				lastFlipY = flipY;

				/*cam.imageGraphics.drawImage(drawClip,
						(int) (((int)(x * paralax.x) - offset.y) - cam.x),
						(int) (((int)(y * paralax.y) - offset.y) - cam.y), null);
						*/
				
				cam.imageGraphics.drawImage(drawClip,
				((int)((x-offset.x) * paralax.x) - (int)(cam.x)),
				((int)((y-offset.y) * paralax.y) - (int)(cam.y)),
				(int)width,
				(int)height,
				null);
				
			}

		}

	}

	public void destroy() {
		super.destroy();
		graphic.flush();
		drawClip.flush();

		animated = false;
		currentAnimation = null;
		anims.clear();
		anims.trimToSize();

	}

}
