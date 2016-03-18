package me.nimnon.nmengine.core;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import me.nimnon.nmengine.util.ImageUtils;

public class Font {
	String characters = "````````````````" + "````````````````" + " !\"#$%&'()*+,-./" + "0123456789:;<=>?" + "@ABCDEFGHIJKLMNO" + "PQRSTUVWXYZ[\\]^_"
			+ "'abcdefghijklmno" + "pqrstuvwxyz{|}~ ";

	BufferedImage fontSrc;

	public Font() {
		try {
			fontSrc = ImageUtils.getImage("/me/nimnon/nmengine/assets/fonts/BitmapFont.png");
			BufferedImage tempImg = new BufferedImage(fontSrc.getWidth(), fontSrc.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
			Graphics2D g2d = tempImg.createGraphics();
			g2d.drawImage(fontSrc, 0, 0, null);
			g2d.dispose();
			fontSrc = tempImg;
			tempImg.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getStringWidth(String string) {
		return string.length() * 8;

	}

	public int getStringHeight(String string, int wrapPoint) {
		if (wrapPoint != 0) {
			return (string.length() * 8 / wrapPoint) * 8;
		}
		return 8;
	}

	public BufferedImage makeString(String string, Color color, Color background) {
		
		int length = getStringWidth(string);
		if (length==0)
			length = 1;

		BufferedImage textImage = new BufferedImage(length, 8, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = textImage.createGraphics();

		BufferedImage clip;

		for (int i = 0; i < string.length(); i++) {

			int charIndex = characters.indexOf(string.charAt(i));
			charIndex = Math.max(Math.min(16 * 16, charIndex), 0);
			clip = ImageUtils.getSlice(charIndex, fontSrc, 8, 8);
			
			clip = ImageUtils.colorFilter(clip, color, Color.white);
			clip = ImageUtils.colorFilter(clip, background, Color.black);
			
			g2d.drawImage(clip, i * 8, 0, null);
		}
		g2d.dispose();

		return textImage;
	}

}
