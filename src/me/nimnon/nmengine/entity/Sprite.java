package me.nimnon.nmengine.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite extends GameObject {

	private BufferedImage image;

	public Sprite() {
		// TODO Auto-generated constructor stub
	}

	public Sprite(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public Sprite(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public void setGraphic(String src) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/" + src));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		width = image.getWidth()*4;
		height = image.getHeight()*4;
	}

	public void create() {
		setGraphic("default.png");
		drawDebug = false;
	}

	public void update() {
		super.update();
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, (int) x, (int) y, image.getWidth()*4, image.getHeight()*4, null);
		super.draw(g2d);
	}

}
