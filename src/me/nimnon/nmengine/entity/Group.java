package me.nimnon.nmengine.entity;

import java.util.ArrayList;

import me.nimnon.nmengine.Game;

public class Group extends Basic {

	public ArrayList<Basic> children = new ArrayList<Basic>();

	public Group() {
		// TODO Auto-generated constructor stub
	}

	public void update() {
		for (int i = 0; i < children.size(); i++) {
			children.get(i).update();
		}
	}

	public void draw() {
		for (int i = 0; i < children.size(); i++) {
			children.get(i).draw();
		}
	}
	
	public void add(Basic object) {
		children.add(object);
	}

}
