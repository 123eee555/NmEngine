package me.nimnon.testGame;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.entity.Camera;
import me.nimnon.nmengine.entity.GameObject;
import me.nimnon.nmengine.entity.Sprite;
import me.nimnon.nmengine.state.State;

public class TestState extends State {

	GameObject box;
	Camera cam;
	
	public TestState() {
		// TODO Auto-generated constructor stub
	}

	public void create() {
		box = new Sprite();
		add(box);
	}

	public void update() {
		super.update();
		box.x += (Game.mouse.xWorld-box.center.x)/9;
		box.y += (Game.mouse.yWorld-box.center.y)/9;
	}

}
