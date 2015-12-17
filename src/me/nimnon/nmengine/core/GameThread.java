package me.nimnon.nmengine.core;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import me.nimnon.nmengine.Game;

/**
 * Contains Update and Graphics loop
 */
public class GameThread extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	/**
	 * Controls the main game loop, should really never be set to false
	 */
	private boolean running = false;

	/**
	 * Updates per second
	 */
	public int tps = 60;

	/**
	 * Called when the GameThread starts.
	 */
	public void run() {
		running = true;

		System.out.println("////////////////////////");
		System.out.printf("%-6s %s %7s \n", "/", " NmEngine", "/");
		System.out.println("////////////////////////");

		gameLoop();
	}

	/**
	 * Method that starts the game loop.
	 */
	private void gameLoop() {
		long now = System.nanoTime();
		long last = System.nanoTime();
		long timer = System.currentTimeMillis();

		int updates = 0;
		int frames = 0;

		long ns = 1000000000;
		long delta = 0;

		while (running) {
			now = System.nanoTime();
			delta += now - last;
			if (delta > ns / tps) {
				update();
				repaint();
				updates++;
				frames++;
				Game.elapsedTime = delta / 1000000000d;

				delta -= ns / tps;
			}
			if (Game.currentState != null) {
				
				
			}
			last = now;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				if(Game.debug)
					System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	/**
	 * Called directly from the game loop, calls update on the state which in
	 * turn updates everything else.
	 */
	private void update() {
		Game.currentState.preUpdate();
		Game.currentState.update();
		Game.currentState.postUpdate();
		Game.activeCamera.update();
		Game.activeCamera.update();
		tps = Game.ticksPerSecond;

		Game.mouse.xWorld = (Game.mouse.x / Game.activeCamera.zoom) + (Game.activeCamera.x);
		Game.mouse.yWorld = (Game.mouse.y / Game.activeCamera.zoom) + (Game.activeCamera.y);
	}

	/**
	 * Redraws the window.
	 */
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;

		if (Game.currentState != null)
			Game.currentState.draw();
		for (int i = 0; i < Game.cameras.size(); i++) {
			Game.cameras.get(i).draw(g2d);
		}
	}

}
