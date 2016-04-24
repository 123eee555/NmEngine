package me.nimnon.nmengine.entity.tile;

import java.awt.image.BufferedImage;

import me.nimnon.nmengine.entity.Basic;
import me.nimnon.nmengine.entity.Sprite;
import me.nimnon.nmengine.util.ImageUtils;

public class TileMap extends Basic {
	
	public double offsetX = 0;
	public double offsetY = 0;
	
	
	private Sprite[] tileGroup;
	private int[] idArray;
	private int widthInTiles;
	private int heightInTiles;
	private int tileWidth;
	private int tileHeight;
	private BufferedImage tileSet;

	public TileMap() {
	}

	public void loadMap(int[] tileArray, String tileSet, int tileWidth, int tileHeight, int widthInTiles, int heightInTiles) {
		idArray = tileArray;
		this.widthInTiles = widthInTiles;
		this.heightInTiles = heightInTiles;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		tileGroup = new Sprite[widthInTiles * heightInTiles];
		if (tileSet != null)
			try {
				this.setTileSet(ImageUtils.getImage(tileSet));
			} catch (Exception e) {
				e.printStackTrace();
			}
		for (int i = 0; i < tileArray.length; i++) {
			int x = (i % widthInTiles);
			int y = (i / widthInTiles);
			addTile(x, y, tileArray[i]);
		}
	}

	public void removeTile(int x, int y) {
		if (!(x >= 0 && y >= 0) && !(x < widthInTiles && y > heightInTiles)) {
			return;
		}
		try {
			tileGroup[(y * widthInTiles) + x] = null;
		} catch (IndexOutOfBoundsException e) {
		}
	}

	public void addTile(int x, int y, int id) {
		// Check if tile is already there
		if (x < 0 || y < 0 || x >= widthInTiles || y >= heightInTiles) {
			return;
		}
		try {
			if (tileGroup[(y * widthInTiles) + x] != null) {
				return;
			}
		} catch (IndexOutOfBoundsException e) {
		}
		if (id >= 1) {
			Sprite tile = new Sprite((x * tileWidth)+offsetX, (y * tileHeight)+offsetY, tileWidth, tileHeight);
			tile.loadGraphic(ImageUtils.getSlice(id - 1, tileSet, tileWidth, tileHeight));
			tile.movable = false;
			tileGroup[(y * widthInTiles) + x] = tile;
		} else {
			tileGroup[(y * widthInTiles) + x] = null;
		}
	}

	// Getters
	public Sprite[] getTileGroup() {
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

	// Overrides
	public void preUpdate() {
	}

	public void update() {
		for (int i = 0; i < tileGroup.length; i++) {
			try {
				if (tileGroup[i] != null)
					tileGroup[i].update();
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}
	}

	public void postUpdate() {
	}

	public void create() {
	}

	public void draw() {
		for (int i = 0; i < tileGroup.length; i++) {
			try {
				if (tileGroup[i] != null)
					tileGroup[i].draw();
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}
	}

	public BufferedImage getTileSet() {
		return tileSet;
	}

	public void setTileSet(BufferedImage tileSet) {
		this.tileSet = tileSet;
	}
	
	public Sprite[] getChildren() {
		return tileGroup;
		
	}
}
