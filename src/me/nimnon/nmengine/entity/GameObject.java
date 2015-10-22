package me.nimnon.nmengine.entity;

import java.awt.Color;
import java.awt.geom.Point2D;

import me.nimnon.nmengine.Game;

public class GameObject extends Basic {

	public double x = 0d;
	public double y = 0d;
	public double width = 16d;
	public double height = 16d;

	public Point2D.Double last = new Point2D.Double(0, 0);
	public Point2D.Double next = new Point2D.Double(0, 0);

	public Point2D.Double maxVelocity = new Point2D.Double(0, 0);
	public Point2D.Double velocity = new Point2D.Double(0, 0);
	public Point2D.Double drag = new Point2D.Double(0, 0);

	public Point2D.Double center = new Point2D.Double(x + (width / 2), y + (height / 2));

	public boolean[] touching = new boolean[4];

	public GameObject() {
		// TODO Auto-generated constructor stub
	}

	public void update() {
		center.setLocation(this.x+(width/2), this.y+(height/2));
	}

	public void draw() {
		for (int i = 0; i < Game.cameras.size(); i++) {
			if (Game.cameras.get(i).isOnScreen(this)) {
				Camera cam = Game.cameras.get(i);
				cam.imageGraphics.setColor(Color.red);
				cam.imageGraphics.drawRect((int) (x - cam.x), (int) (y - cam.y), (int) width, (int) height);
			}
		}
	}

}
