package me.nimnon.nmengine.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageUtils {
	
	/**
	 * Returns an image slice from specified image, based on index, useful for picking
	 * images out of a spritesheet, terrible if you wanna use a texture atlas.
	 * @param index index of needed image
	 * @param image source image
	 * @param marqueeWidth width of each sprite section
	 * @param marqueeHeight height of each sprite
	 * @return retImage sliced image
	 */
	public static BufferedImage getSlice(int index, BufferedImage image, int marqueeWidth, int marqueeHeight) {
		
		int x = (index*marqueeWidth%image.getWidth());
		int y = (index*marqueeWidth/image.getWidth())* marqueeHeight;
		
		int w = marqueeWidth;
		int h = marqueeHeight;
		
		BufferedImage subImage = image.getSubimage(x, y, w, h);
		
		
		BufferedImage retImage = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = retImage.createGraphics();
		g2d.drawImage(subImage, 0, 0, null);
		g2d.dispose();
		subImage.flush();
		return retImage;
	}
	
	public static BufferedImage getImage(String path) throws IOException {
		URL img = new Byte((byte) 0).getClass().getResource(path);
		return ImageIO.read(img);
	}
	
	/**
	 * Flips sprite on x axis
	 * @param image Source image
	 * @return retImage Flipped image
	 */
	public static BufferedImage flipX(BufferedImage image) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-image.getWidth(), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter(image, null);
	}
	
	/**
	 * Flips sprite on y axis
	 * @param image Source image
	 * @return retImage Flipped image
	 */
	public static BufferedImage flipY(BufferedImage image) {
		AffineTransform ty = AffineTransform.getScaleInstance(1, -1);
		ty.translate(0, -image.getHeight());
		AffineTransformOp op = new AffineTransformOp(ty, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter(image, null);
	}
	
	public static BufferedImage colorFilter(BufferedImage original, Color replaceColor, Color colorToReplace) {
		
		BufferedImage replacement = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		int[] pixels = new int[replacement.getWidth() * replacement.getHeight()];
		original.getRGB(0, 0, replacement.getWidth(), replacement.getHeight(), pixels, 0, replacement.getWidth());
		
		for (int i2 = 0; i2 < pixels.length; i2++) {
			if (pixels[i2] == colorToReplace.hashCode()) {
				pixels[i2] = replaceColor.hashCode();
			}
		}
		
		replacement.setRGB(0, 0, replacement.getWidth(), replacement.getHeight(), pixels, 0, replacement.getWidth());
		replacement.flush();
		
		Graphics2D g2d = original.createGraphics();
		
		g2d.drawImage(replacement,0,0,null);
		
		g2d.dispose();
		return replacement;
		
	}
}
