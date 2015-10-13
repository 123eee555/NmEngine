package me.nimnon.testGame;

import me.nimnon.nmengine.Game;

public class Main {

	public static void main(String[] args) {
		new Game(600, 600, "NmGame", new MenuState(), 60);
	}

}
