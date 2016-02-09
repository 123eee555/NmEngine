package me.nimnon.nmengine.entity.ui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.util.ImageUtils;

public class NineSlice extends UIBasic {

	private int sliceWidth = 3;
	private int sliceHeight = 3;

	protected BufferedImage[] sliceArray = new BufferedImage[9];
	
	BufferedImage graphic;

	public NineSlice(double x, double y) {
		this.width = 10;
		this.height = 10;
		this.x = x;
		this.y = y;
	}

	public NineSlice(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	public NineSlice(Rectangle rect) {
		super(rect);
	}
	
	public void createNineSliceFromImage(String path) {
		try {
			BufferedImage source = ImageUtils.getImage(path);
			sliceWidth = source.getWidth() / 3;
			sliceHeight = source.getHeight() / 3;
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < sliceArray.length; i++) {
			try {
				sliceArray[i] = ImageUtils.getSlice(i, ImageUtils.getImage(path), sliceWidth, sliceHeight);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void create() {
		super.create();
		createNineSliceFromImage("/me/nimnon/nmengine/assets/9slice.png");
	}

	public void draw() {
		Graphics2D g2d = Game.activeCamera.imageGraphics;

		for (int dx = 1; dx <= (int) (width - 1) / sliceWidth - 1; dx++) {
			for (int dy = 1; dy <= (int) (height - 1) / sliceHeight - 1; dy++) {
				g2d.drawImage(sliceArray[4], (int) x + (dx * sliceWidth), (int) y + (dy * sliceHeight), null);
			}
		}
		
		for (int dx = 1; dx <= (int) (width - 1) / sliceWidth - 1; dx++) {
			g2d.drawImage(sliceArray[1], (int) x + (dx * sliceWidth), (int) y, null);
		}
		
		for (int dx = 1; dx <= (int) (width - 1) / sliceWidth - 1; dx++) {
			g2d.drawImage(sliceArray[1], (int) x + (dx * sliceWidth), (int) (y + height) - sliceWidth, null);
		}
		
		for (int dy = 1; dy <= (int) (height - 1) / sliceHeight - 1; dy++) {
			g2d.drawImage(sliceArray[3], (int) x, (int) y + (dy * sliceHeight), null);
		}
		
		for (int dy = 1; dy <= (int) (height - 1) / sliceHeight - 1; dy++) {
			g2d.drawImage(sliceArray[5], (int) (x + width) - sliceWidth, (int) y + (dy * sliceHeight), null);
		}
		
		g2d.drawImage(sliceArray[0], (int) x, (int) y, null);
		g2d.drawImage(sliceArray[2], (int) (x + width) - sliceWidth, (int) y, null);
		g2d.drawImage(sliceArray[6], (int) x, (int) (y + height) - sliceWidth, null);
		g2d.drawImage(sliceArray[8], (int) (x + width) - sliceWidth, (int) (y + height) - sliceWidth, null);
	}
}
