package me.nimnon.nmengine.state;

import java.awt.Graphics2D;

public interface GameState {

	public abstract void create();
	public abstract void draw(Graphics2D g2d);
	public abstract void update();
}
