package me.nimnon.testGame;

import java.awt.Font;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.entity.Sprite;
import me.nimnon.nmengine.entity.tile.TileMap;
import me.nimnon.nmengine.entity.ui.Button;
import me.nimnon.nmengine.entity.ui.Text;
import me.nimnon.nmengine.state.State;
import me.nimnon.nmengine.util.Physics;

public class TestState extends State {

	Sprite character;
	Text text;
	TileMap map;

	public TestState() {
		// TODO Auto-generated constructor stub
	}

	public void create() {
		character = new Sprite(0, 0, 32, 32);
		character.drag.setLocation(500, 0);
		character.setGraphic("NmRun.png", true, 32, 32);

		character.width = 20;
		character.height = 32;
		character.offsetX = 4;
		character.offsetY = 3;
		character.maxVelocity.x = 512;
		character.maxVelocity.y = 512;
		character.drawDebug = true;
		character.addAnim("Idle", new int[] { 3 }, 0, false);
		character.addAnim("Run", new int[] { 0, 1, 2, 3, 4 }, 12, true);
		character.addAnim("Jump", new int[] { 4 }, 0, false);
		add(character);
		
		text = new Text(10, 10, "Yes", 11);
		text.font = new Font("Monospaced", Font.PLAIN, 8);
		text.paralax.setLocation(0,0);
		add(text);

		map = new TileMap();

		int[][] tileArray = {
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1}
		};

		map.buildTileMap("TilemapBasic.png", tileArray, 16, 16);

		add(map);
		Button butt = new Button("YOOO", 32, 32, 128, 32) {

			/*
			 * Because java does not support defining callback functions through
			 * parameters you must define the onPress action during
			 * instantiation, to get around this you must define your fancy
			 * fancy onclick logic around these limits! Sorry!
			 */

			@Override
			public void onPress() {
				character.x = Math.random()*100;
				character.y = Math.random()*100;
			}

		};
		add(butt);

		Game.activeCamera.folow(character, 1);
	}
	
	public void update() {
		super.update();

		text.text = "PlayerX: "+String.valueOf(character.x).substring(0,6) +" PlayerY: "+String.valueOf(character.y).substring(0,6);
		
		Physics.collide(character, map.tileGroup);

		character.acceleration.y = 1600;
		character.acceleration.x = 0;

		if (character.y > Game.activeCamera.height) {
			character.y = -200;
		}

		if (character.touching[0]) {
			if (Math.abs(character.velocity.x) > 32) {
				character.playAnim("Run");
				character.offsetY = -0;
			} else {
				character.playAnim("Idle");
				character.offsetY = -3;
			}

		} else {
			character.playAnim("Jump");
			character.offsetY = 0;
		}

		if (Game.getKeyPressed(65)) {
			character.acceleration.x = -1000;
			character.flipX = true;
		} else if (Game.getKeyPressed(68)) {
			character.acceleration.x = 1000;
			character.flipX = false;
		}

		if (Game.getKeyPressed(32) && character.touching[0]) {
			character.velocity.y = -600;

		}

	}

}
