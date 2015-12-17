package me.nimnon.nmengine;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public class Fonts {

	/**
	 * A font bearing a likeness to the text in Yoshi's island, cartoony and fun!
	 */
	public Font yoster;

	/**
	 * A more serious font than yoster.
	 */
	public Font boocity;
	
	/**
	 * Core class used as a font registry, embed new fonts here
	 */
	public Fonts() {
		try {
			yoster = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/yoster.ttf")).deriveFont(Font.PLAIN, 20);
			boocity = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/boocity.ttf")).deriveFont(Font.PLAIN, 20);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(yoster);
		
		GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(boocity);
	}

}
