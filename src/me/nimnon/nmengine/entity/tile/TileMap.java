package me.nimnon.nmengine.entity.tile;

import java.awt.image.BufferedImage;

import me.nimnon.nmengine.entity.Basic;
import me.nimnon.nmengine.entity.Group;
import me.nimnon.nmengine.util.ImageUtils;

public class TileMap extends Basic {

	private Group tileGroup;
	
	private int[] idArray;
	
	private int widthInTiles;
	private int heightInTiles;
	
	private int tileWidth;
	private int tileHeight;
	
	private BufferedImage tileSet;
	
	public TileMap() {
		
	}
	
	public void loadMap(int[] tileArray, String tileSet, int tileWidth, int tileHeight) {
		idArray = tileArray;
		
		try {
			this.setTileSet(ImageUtils.getImage(tileSet));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void removeTile(int x, int y) {
		
	}
	
	public void addTile() {
		
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
	
	public int[] getTileArray() {
		return idArray;
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

	public BufferedImage getTileSet() {
		return tileSet;
	}

	public void setTileSet(BufferedImage tileSet) {
		this.tileSet = tileSet;
	}

}
