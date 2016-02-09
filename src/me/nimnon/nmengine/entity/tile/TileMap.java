package me.nimnon.nmengine.entity.tile;

import me.nimnon.nmengine.entity.Basic;
import me.nimnon.nmengine.entity.Group;

public class TileMap extends Basic {

	private Group tileGroup;
	
	private int widthInTiles;
	private int heightInTiles;
	
	private int tileWidth;
	private int tileHeight;
	
	public TileMap() {
		
	}
	
	public void loadMap(int[] tileArray, String tileSet, int tileWidth, int tileHeight) {
		
	}
	
	//Getters
	
	public Group getTileGroup() {
		return tileGroup;
	}

	public int getTileWidth() {
		return tileWidth;
	}
	
	public int getTileHeight() {
		return tileHeight;
	}
	
	public int getWidthInTiles() {
		return widthInTiles;
	}
	
	public int getHeightInTiles() {
		return heightInTiles;
	}
	
	//Overrides

	public void preUpdate() {

	}

	public void update() {

	}

	public void postUpdate() {

	}

	public void create() {

	}

	public void draw() {
		
	}

}
