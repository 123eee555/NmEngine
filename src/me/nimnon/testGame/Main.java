package me.nimnon.testGame;

import java.awt.Color;

import me.nimnon.nmengine.Game;

public class Main {

	public static void main(String[] args) {
		new Game(600, 600, "NmGame", new TestState(), 60, 2d, new Color(15, 50, 75));
	}

}
