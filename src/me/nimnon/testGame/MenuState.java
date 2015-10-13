package me.nimnon.testGame;

import java.awt.Color;
import java.awt.Graphics2D;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.Mouse;
import me.nimnon.nmengine.entity.GameObject;
import me.nimnon.nmengine.entity.Group;
import me.nimnon.nmengine.entity.Sprite;
import me.nimnon.nmengine.entity.Text;
import me.nimnon.nmengine.state.State;

public class MenuState extends State {

	Color color;
	GameObject box;
	GameObject box2;
	Group group;

	public void create() {
		group = new Group();
		add(group);
		
		box = new Sprite();
		box.drag.setLocation(1.2f, 1.2f);
		box.color = Color.RED;
		
		add(box);

		box2 = new GameObject(0, 400);
		box2.width = 64;
		box2.height = 64;
		group.add(box2);
		
		box2 = new GameObject(66, 400);
		box2.width = 64;
		box2.height = 64;
		group.add(box2);
		
		box2 = new GameObject(66+66, 400);
		box2.width = 64;
		box2.height = 64;
		group.add(box2);
		
		box2 = new GameObject(66+66+66, 400);
		box2.width = 64;
		box2.height = 64;
		group.add(box2);
		
		box2 = new GameObject(66+66+66+66, 466);
		box2.width = 64;
		box2.height = 64;
		group.add(box2);
		
		box2 = new GameObject(66+66+66+66+66, 400);
		box2.width = 64;
		box2.height = 64;
		group.add(box2);
		
		box2 = new GameObject(66+66+66+66+66, 400);
		box2.width = 64;
		box2.height = 64;
		group.add(box2);
		
		Text text = new Text(26, 26, 32);
		text.setText("YES", 32);
		add(text);
		
	}

	public void update() {
		super.update();

		

		box.drag.setLocation(1.1f, 1.1f);

		box.acceleration.x = Game.mouse.x-box.center.x;
		box.acceleration.y = Game.mouse.y-box.center.y;
		

		group.collide(box);
		
		
	}

	public void draw(Graphics2D g2d) {
		super.draw(g2d);
	}

}
