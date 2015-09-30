package me.nimnon.testGame;

import java.awt.Color;
import java.awt.Graphics2D;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.entity.GameObject;
import me.nimnon.nmengine.state.State;

public class MenuState extends State {

	Color color;
	GameObject box;
	
	public void create() {
		box = new GameObject();
		add(box);
	}
	
	public void update() {
		super.update();
		
		box.x += (Game.mouse.x-box.x)/1.2;
		box.y += (Game.mouse.y-box.y)/1.2;
	}
	
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
	}

}
