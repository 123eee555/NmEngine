package me.nimnon.testGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.state.State;

public class MenuState extends State {

	Color color;
	public MenuState() {
		// TODO Auto-generated constructor stub
		color = Color.getHSBColor((float)Math.random(),0.5f,1f);
	}
	
	public void update() {
		super.update();
		color = Color.getHSBColor((float)Math.random(),0.5f,1f);
		Game.ticksPerSecond = new Random().nextInt(4)+4;
	}
	
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
		g2d.setColor(color);
		g2d.fillRect(0, 0, 600, 600);
	}

}
