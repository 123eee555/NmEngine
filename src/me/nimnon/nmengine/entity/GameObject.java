package me.nimnon.nmengine.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import com.sun.javafx.geom.Point2D;

import me.nimnon.nmengine.Game;

public class GameObject extends Basic {

	public Point2D acceleration = new Point2D(0, 0);
	/**
	 * Units traveled per second
	 */
	public Point2D velocity = new Point2D(0, 0);
	/**
	 * How much velocity is divided by every second
	 */
	public Point2D drag = new Point2D(1f,1f);
	/**
	 * Max velocity of the object on each axis.
	 */
	public Point2D maxVelocity = new Point2D(0,0);
	/**
	 * Last position on each axis
	 */
	public Point last = new Point(0,0);
	/**
	 * Next predicted position on each axis
	 */
	public Point next = new Point(0,0);
	
	public boolean[] touching = new boolean[4];
	public Color color = Color.black;
	
	public GameObject() {
		super();
		x = 0;
		y = 0;
	}
	
	public GameObject(int x,int y) {
		super(x,y,32,32);
		this.x = x;
		this.y = y;
		last.x = x;
		last.y = y;
	}
	
	public GameObject(int x,int y,int width, int height) {
		super(x,y,width,height);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		last.x = x;
		last.y = y;
	}

	public void preUpdate() {
		last.x = (int) x;
		last.y = (int) y;
		next.x = (int) (x+velocity.x/Game.ticksPerSecond);
		next.y = (int) (y+velocity.y/Game.ticksPerSecond);
	}
	
	public void update() {
		
	}
	
	public void postUpdate() {
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
		
		touching[0] = false;
		touching[1] = false;
		touching[2] = false;
		touching[3] = false;
	}

	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(color);
		g2d.fillRect((int)x, (int)y, width, height);
	}

}
