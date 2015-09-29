package me.nimnon.nmengine.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import com.sun.javafx.geom.Point2D;

import me.nimnon.nmengine.Game;

public class GameObject extends Basic {

	public float x = 0f;
	public float y = 0f;
	
	
	public Point2D acceleration = new Point2D(320, 320);
	public Point2D velocity = new Point2D(0, 0);
	public Point2D drag = new Point2D(1.2f, 1.2f);
	public Point2D maxVelocity = new Point2D(32,32);
	
	public int width = 32;
	public int height = 32;
	
	public GameObject() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {

		velocity.x += acceleration.x;
		velocity.y += acceleration.y;
		
		if(maxVelocity.x > 0)
			velocity.x = Math.min(Math.max(velocity.x, -maxVelocity.x),maxVelocity.x);
		if(maxVelocity.x > 0)
			velocity.y = Math.min(Math.max(velocity.y, -maxVelocity.y),maxVelocity.y);
		
		x += velocity.x/Game.ticksPerSecond;
		y += velocity.y/Game.ticksPerSecond;
		
		velocity.x /= drag.x;
		velocity.y /= drag.y;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(Color.BLACK);
		g2d.fillRect((int)x, (int)y, width, height);
	}

}
