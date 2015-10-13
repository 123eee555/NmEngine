package me.nimnon.nmengine.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Text extends Basic {

	public String text = "Text";
	
	public int size = 8;
	
	public Color color = Color.black;
	
	public Text() {
		super(0,0,128,8);
	}
	
	public Text(int x, int y) {
		super(x, y, 128, 8);
	}
	
	public Text(int x, int y, int size) {
		super(x, y, 128, size);
	}
	
	public void setText(String text, int size)
	{
		this.text = text;
		this.size = size;
	}
	
	public void draw(Graphics2D g2d)
	{
		super.draw(g2d);
		g2d.setColor(color);
		g2d.setFont(new Font("Monospaced", Font.PLAIN, size));
		g2d.drawString(text, x, y+(size/2));
	}

}
