package me.nimnon.nmengine.entity.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.core.Camera;
import me.nimnon.nmengine.entity.Basic;
import me.nimnon.nmengine.entity.Group;

public class TileMap extends Basic {

	/**
	 * The image used to draw the tilemap after building
	 */
	private BufferedImage levelGraphics;

	/**
	 * Graphics object used to store the tile palette to levelGraphics
	 */
	private BufferedImage tileGraphics;

	/**
	 * Width and Height of the tilemap in tiles
	 */
	public double widthInTiles, heightInTiles = 0;

	/**
	 * Width and Height of each tile respectively
	 */
	public double tileWidth, tileHeight = 0;

	/**
	 * 2d integer array used to build tilemap
	 */
	public int[][] tileData;

	/**
	 * Group used to store the tile objects for collision, to be changed to a
	 * quadTree in the future
	 */
	public Group tileGroup;

	/**
	 * Instance new tilemap
	 */
	public TileMap() {
		tileGroup = new Group();
	}

	/**
	 * Builds your tilemap and draws graphics from pathToTileGraphics TileData
	 * must be a 2d array of integers tileWidth and tileHeight correspond to the
	 * width and height in pixels of each tile. The Tilemap's tile size must
	 * correspond with the tileWidth and tileHeight
	 * 
	 * @param pathToTileGraphics Path to the tileset in the assets directory
	 * @param tileData 2d integer array containing tile data
	 * @param tileWidth Width of each tile in the tileset
	 * @param tileHeight Height of each tile in the tileset
	 */
	public void buildTileMap(String pathToTileGraphics, int[][] tileData, int tileWidth, int tileHeight) {
		this.tileData = tileData;
		setTileGraphics(pathToTileGraphics);

		this.heightInTiles = tileData.length;
		this.widthInTiles = tileData[0].length;

		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;

		levelGraphics = new BufferedImage((int) widthInTiles * tileWidth, (int) heightInTiles * tileHeight, BufferedImage.TYPE_INT_ARGB);

		Graphics2D levelG2D = levelGraphics.createGraphics();

		if (tileData.length == heightInTiles) {
			this.tileData = tileData;
			for (int y = 0; y < heightInTiles; y++) {
				for (int x = 0; x < widthInTiles; x++) {
					if (tileData[y][x] > 0) {
						int index = tileData[y][x];
						tileGroup.add(new Tile(x * tileWidth, y * tileHeight, tileWidth, tileHeight));
						BufferedImage tileGraphic = tileGraphics.getSubimage((int) (((index - 1) * tileWidth) % tileGraphics.getWidth()),
								(int) (Math.floor((index - 1) / (tileGraphics.getWidth() / tileWidth)) * tileHeight), tileWidth, tileHeight);
						levelG2D.drawImage(tileGraphic, x * tileWidth, y * tileHeight, null);
					}
				}
			}
			levelG2D.dispose();
		} else {
			System.err.println("tileData does not match specified dimensions!");
		}
	}

	/**
	 * Sets tileGraphic to specified path, does not change the look and feel of
	 * tiles untill the map is reloaded
	 * 
	 * @param path Path to tilesheet
	 */
	public void setTileGraphics(String path) {
		File img = new File("assets/" + path);
		try {
			tileGraphics = ImageIO.read(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void preUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void postUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		for (int i = 0; i < Game.cameras.size(); i++) {
			if (Game.cameras.get(i).isOnScreen(this)) {
				Camera cam = Game.cameras.get(i);
				cam.imageGraphics.drawImage(levelGraphics, (int) (0 - cam.x), (int) (0 - cam.y), null);
			}
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
