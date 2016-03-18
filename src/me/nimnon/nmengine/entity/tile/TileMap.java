package me.nimnon.nmengine.entity.tile;

import java.awt.Color;

import me.nimnon.nmengine.entity.Group;
import me.nimnon.nmengine.entity.Sprite;

public class TileMap extends Group {

	private int[] idArray;

	private int widthInTiles;
	private int heightInTiles;

	private int tileWidth;
	private int tileHeight;

	public TileMap() {

	}

	public void loadMap(int[] tileArray, String tileSet, int tileWidth, int tileHeight, int widthInTiles, int heightInTiles) {
		idArray = tileArray;
		this.widthInTiles = widthInTiles;
		this.heightInTiles = heightInTiles;

		for (int index = 0; index < tileArray.length; index++) {
			// System.out.println("X: " + (index % widthInTiles) + " | Y: " + Math.floor(index / heightInTiles));

			if (tileArray[index] > 0) {
				Sprite tile = new Sprite((int) (index % widthInTiles) * tileWidth, (int) (index / widthInTiles) * tileHeight, tileWidth, tileHeight);
				add(tile);
			}
		}
	}

	public void removeTile(int tileX, int tileY) {

	}

	public void addTile(int tileX, int tileY) {

	}

	// Getters

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

	// Overrides

	public void preUpdate() {
		super.preUpdate();
	}

	public void update() {
		super.update();
	}

	public void postUpdate() {
		super.postUpdate();
	}

	public void create() {
		super.create();
	}

	public void draw() {
		super.draw();
	}

}
