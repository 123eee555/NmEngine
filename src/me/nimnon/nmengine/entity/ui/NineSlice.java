package me.nimnon.nmengine.entity.ui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.core.Camera;
import me.nimnon.nmengine.util.ImageUtils;

public class NineSlice extends UIBasic {

	private int sliceWidth = 3;
	private int sliceHeight = 3;

	protected BufferedImage[] sliceArray = new BufferedImage[9];

	BufferedImage graphic;
	Graphics2D g2d;

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
		for (int i = 0; i < Game.cameras.size(); i++) {
			Camera cam = Game.cameras.get(i);
			if (cam.isOnScreen(this)) {
				
				graphic = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_ARGB);
				g2d = graphic.createGraphics();
				
				for (int dx = 1; dx <= (int) (width - 1) / sliceWidth - 1; dx++) {
					for (int dy = 1; dy <= (int) (height - 1) / sliceHeight - 1; dy++) {
						g2d.drawImage(sliceArray[4], (int) (dx * sliceWidth), (int) (dy * sliceHeight), null);
					}
				}

				for (int dx = 1; dx <= (int) (width - 1) / sliceWidth - 1; dx++) {
					g2d.drawImage(sliceArray[1], (int) (dx * sliceWidth), 0, null);
				}

				for (int dx = 1; dx <= (int) (width - 1) / sliceWidth - 1; dx++) {
					g2d.drawImage(sliceArray[7], (int) (dx * sliceWidth), (int) (height) - sliceWidth, null);
				}

				for (int dy = 1; dy <= (int) (height - 1) / sliceHeight - 1; dy++) {
					g2d.drawImage(sliceArray[3], 0, (int) (dy * sliceHeight), null);
				}

				for (int dy = 1; dy <= (int) (height - 1) / sliceHeight - 1; dy++) {
					g2d.drawImage(sliceArray[5], (int) (width) - sliceWidth, (int) (dy * sliceHeight), null);
				}

				g2d.drawImage(sliceArray[0], (int) 0, (int) 0, null);
				g2d.drawImage(sliceArray[2], (int) (0 + width) - sliceWidth, (int) 0, null);
				g2d.drawImage(sliceArray[6], (int) 0, (int) (0 + height) - sliceWidth, null);
				g2d.drawImage(sliceArray[8], (int) (0 + width) - sliceWidth, (int) (0 + height) - sliceWidth, null);

				cam.imageGraphics.drawImage(graphic, ((int) (x) - (int) (cam.x - cam.getOffsetX())),
						((int) (y) - (int) (cam.y - cam.getOffsetY())), null);
			}
		}

	}
}
