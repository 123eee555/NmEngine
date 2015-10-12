package me.nimnon.testGame;

import java.awt.Color;
import java.awt.Graphics2D;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.Mouse;
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
		
		box.drag.setLocation(1.2f,1.2f);
		box.color = Color.RED;
		
		box2.width=400;
		box2.height=64;
		
		box3 = new GameObject(402,400);
		box3.width = 64;
		box3.height = 128+64;
		
		add(box3);
	}
	
	public void update() {
		super.update();
		
		Game.collide(box, box3);
		Game.collide(box, box2);
		
		box.drag.setLocation(1.1f,1);
		
		box.acceleration.x = 32;
		box.acceleration.y = 64;
		
		if(Game.mouse.mouse1Down)
		{
			box.acceleration.y = -16;
		}
		
		if(box.x > 600)
		{
			box.x = -32;
			box.y = 0;
		}
		
	}
	
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
	}

}
