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

	private static int[][] thresholdMap = new int[][] {
		{1,9,3,11},
		{13,5,15,7},
		{4,12,2,10},
		{16,8,14,6}
	};
	/**
	 * Returns an image slice from specified image, based on index, useful for
	 * picking images out of a sprite sheet.
	 * 
	 * @param index
	 *            index of needed image
	 * @param image
	 *            source image
	 * @param marqueeWidth
	 *            width of each sprite section
	 * @param marqueeHeight
	 *            height of each sprite
	 * @return retImage sliced image
	 */
	public static BufferedImage getSlice(int index, BufferedImage image, int marqueeWidth, int marqueeHeight) {

		int x = (index * marqueeWidth % image.getWidth());
		int y = (index * marqueeWidth / image.getWidth()) * marqueeHeight;

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

	public static void ditherImage(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		int oldpixel;
		int newpixel;

		int[] pixels = new int[w * h];
		image.getRGB(0, 0, w, h, pixels, 0, w);

		int[] newpixels = new int[w * h];

		for (int x = 0; x < w * h; x++) {
			int rX = x % w;
			int rY = (int) Math.floor(x / w);
			oldpixel = ditherPixel(pixels[x],rX,rY);
			// System.out.println(rX + "|" + rY);
			newpixel = quantize(oldpixel);
			newpixels[x] = newpixel;
		}
		image.setRGB(0, 0, w, h, newpixels, 0, w);
	}
	public static int ditherPixel(int oldpixel, int x, int y) {

		int a = (oldpixel >> 24) & 0xff;
		int r = (oldpixel >> 16) & 0xff;
		int g = (oldpixel >> 8) & 0xff;
		int b = (oldpixel >> 0) & 0xff;

		int color = Math.min(0xff,a + thresholdMap[y%4][x%4]) << 24 |  Math.min(0xff,r + thresholdMap[y%4][x%4]) << 16 | Math.min(0xff, g + thresholdMap[y%4][x%4]) << 8 | (int) Math.min(0xff, b + thresholdMap[y%4][x%4]) << 0;
		return color;
	}
	public static int quantize(int oldpixel) {

		int a = (oldpixel >> 24) & 0xff;
		int r = (oldpixel >> 16) & 0xff;
		int g = (oldpixel >> 8) & 0xff;
		int b = (oldpixel >> 0) & 0xff;

		int color = (int) ((a / 32) * (255.0 / 7)) << 24 | (int) ((r / 32) * (255.0 / 7)) << 16 | (int) ((g / 32) * (255.0 / 7)) << 8 | (int) ((b / 32) * (255.0 / 7)) << 0;
		return color;
	}

	public static BufferedImage getImage(String path) throws IOException {
		URL img = new Byte((byte) 0).getClass().getResource(path);
		return ImageIO.read(img);
	}

	/**
	 * Flips sprite on x axis
	 * 
	 * @param image
	 *            Source image
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
	 * 
	 * @param image
	 *            Source image
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

		g2d.drawImage(replacement, 0, 0, null);

		g2d.dispose();
		return replacement;

	}
}
