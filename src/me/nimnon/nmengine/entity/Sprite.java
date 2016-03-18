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
	
	private Color color = Color.getHSBColor((float)Math.random(), 0.8f, 1f);

	private boolean animated;
	private ArrayList<Animation> anims = new ArrayList<>();
	private int animIndex = 0;
	private int animFrame = 0;
	private Animation currentAnimation;
	private double animTime = 0;
	private int sliceWidth;
	private int sliceHeight;

	public boolean scales = false;
	public int scale = 1;
	
	public boolean flipX;
	private boolean lastFlipX = false;
	public boolean flipY;
	private boolean lastFlipY = false;

	public Point offset = new Point(0, 0);
	public int rotation;
	private int lastRot;
	

	// Constructors
	public Sprite() {
		super();
		makeGraphic(12, 12, color);
	}

	public Sprite(double x, double y) {
		super(x, y);
		makeGraphic(12, 12, color);
	}

	public Sprite(double x, double y, double width, double height) {
		super(x, y, width, height);
		makeGraphic((int) width, (int) height, color);
	}

	public Sprite(Rectangle rect) {
		super(rect);
		makeGraphic((int) rect.x, (int) rect.y, color);
	}

	// Graphic Methods
	public void makeGraphic(int width, int height, Color color) {
		this.animated = false;
		this.width = width;
		this.height = height;
		this.graphic = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = this.graphic.createGraphics();

		g2d.setColor(color);
		g2d.fillRect(0, 0, (int) width, (int) height);
		g2d.dispose();
		this.drawClip = ImageUtils.getSlice(0, graphic, width, height);
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
			makeGraphic(12, 12, color);
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
			makeGraphic(12, 12, color);
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		makeGraphic((int) graphic.getWidth(), (int) graphic.getHeight(), color);
	}

	public int getSpriteHeight() {
		if (!scales)
			return drawClip.getHeight();
		else
			return (int) height;
	}

	public int getSpriteWidth() {
		if (!scales)
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
					this.animFrame = 0;
					updateClip();
				}
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
				this.animFrame = (animFrame + 1) % this.currentAnimation.frames.length;
			else if (this.animFrame < this.currentAnimation.frames.length) {
				animFrame++;
			}
			this.animTime = 0;
			this.animIndex = (this.currentAnimation.frames[animFrame]);

		}
		if (frame != animIndex) {
			updateClip();
		}
	}

	public void updateClip() {
		if(animated)
			drawClip = ImageUtils.getSlice(this.currentAnimation.frames[animFrame], graphic, sliceWidth, sliceHeight);
		else
			drawClip = ImageUtils.getSlice(0, graphic, (int)width, (int)height);
		if (flipX) {
			drawClip = ImageUtils.flipX(drawClip);
		}
		if (flipY) {
			drawClip = ImageUtils.flipY(drawClip);
		}
		if (rotation != 0) {
			drawClip = ImageUtils.rotate(drawClip, rotation, false);
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

				if (flipX != lastFlipX || flipY != lastFlipY || rotation != lastRot) {
					updateClip();
				}

				lastRot = rotation;
				lastFlipX = flipX;
				lastFlipY = flipY;
				

				if (!scales)
					cam.imageGraphics.drawImage(drawClip, (int) (((int) (x - offset.x) * paralax.x) - (int)(cam.x - cam.getOffsetX())),
							(int) (((int) (y - offset.y) * paralax.y) - (int)(cam.y - cam.getOffsetY())), null);
				else
					cam.imageGraphics.drawImage(drawClip, (int) (((int) (x - offset.x) * paralax.x) - (int) (cam.x - cam.getOffsetX())),
							((int) ((int) (y - offset.y) * paralax.y) - (int) (cam.y - cam.getOffsetY())), (int) drawClip.getWidth()*scale, (int) (int) drawClip.getHeight()*scale, null);

				if (Game.debug) {
					if(movable)
						cam.imageGraphics.setColor(Color.red);
					else
						cam.imageGraphics.setColor(Color.green);
					cam.imageGraphics.drawRect(((int) ((x) * paralax.x) - (int) (cam.x - cam.getOffsetX())), ((int) ((y) * paralax.y) - (int) (cam.y - cam.getOffsetY())), (int) width - 1,
							(int) height - 1);
				}

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
