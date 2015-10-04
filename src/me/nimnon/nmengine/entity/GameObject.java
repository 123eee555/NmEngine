package me.nimnon.nmengine.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import com.sun.javafx.geom.Point2D;

import me.nimnon.nmengine.Game;

public class GameObject extends Basic {

	public Point2D acceleration = new Point2D(0, 0);
	public Point2D velocity = new Point2D(0, 0);
	public Point2D drag = new Point2D(1f, 1f);
	public Point2D maxVelocity = new Point2D(0,0);
	public Point2D last = new Point2D(0,0);
	
	public boolean[] touching = new boolean[4];
	public Color color = Color.black;
	
	public int width = 32;
	public int height = 32;
	
	public GameObject() {
		// TODO Auto-generated constructor stub
	}
	
	public GameObject(float x,float y) {
		this.x = x;
		this.y = y;
		last.x = x;
		last.y = y;
	}

	@Override
	public void update() {
		
		last.x = x;
		last.y = y;
		
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
	
	public void postUpdate() {
		touching[0] = false;
		touching[1] = false;
		touching[2] = false;
		touching[3] = false;
	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(color);
		g2d.fillRect((int)x, (int)y, width, height);
	}


}
