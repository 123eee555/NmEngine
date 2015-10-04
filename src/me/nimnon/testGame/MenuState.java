package me.nimnon.testGame;

import java.awt.Color;
import java.awt.Graphics2D;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.entity.GameObject;
import me.nimnon.nmengine.state.State;

public class MenuState extends State {

	Color color;
	GameObject box;
	GameObject box2;
	GameObject box3;
	//GameObject box4;
	
	public void create() {
		box = new GameObject(32, 32);
		box2 = new GameObject(0,400);
		add(box);
		add(box2);
		
		box.drag.setLocation(1.03f,1.03f);
		
		box2.width=400;
		box2.height=64;
		
		box3 = new GameObject(400,400-128);
		box3.width = 64;
		box3.height = 128+64;
		
		add(box3);
	}
	
	public void update() {
		super.update();
		
		box.acceleration.x = (Game.mouse.x-box.x-(box.width/2))/1;
		box.acceleration.y = (Game.mouse.y-box.y-(box.height/2))/1;
		
		Game.collide(box, box2);
		Game.collide(box, box3);
	}
	
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
	}

}
