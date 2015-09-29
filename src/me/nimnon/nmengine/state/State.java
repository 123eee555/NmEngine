package me.nimnon.nmengine.state;

import java.awt.Graphics2D;
import java.util.ArrayList;

import me.nimnon.nmengine.entity.Basic;

public class State implements GameState {

	private ArrayList<Basic> children;
	
	public State() {
		children = new ArrayList<>();
	}
	
	public void create() {
		
	}
	
	public void update() {
		for(int i = 0; i < children.size(); i++)
		{
			children.get(i).update();
		}
	}
	
	public void draw(Graphics2D g2d) {
		for(int i = 0; i < children.size(); i++)
		{
			children.get(i).draw(g2d);
		}
	}
	
	public void add(Basic object)
	{
		children.add(object);
	}
	
	public void destroy()
	{
		children = null;
	}

}
