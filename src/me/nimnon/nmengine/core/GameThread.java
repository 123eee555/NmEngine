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
		long lastTime = System.nanoTime();
		double ns = 1000000000 / Game.ticksPerSecond;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				update();
				updates++;
				delta--;
				ns = 1000000000 / Game.ticksPerSecond;
			}
			repaint();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
			
			try{ 
				if((now + ns - System.nanoTime())/1000000 > 0) {
					Thread.sleep((long) ((now + ns - System.nanoTime())/1000000));
				}
			} catch( InterruptedException e ) {
				
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
		for (int i = 0; i < Game.cameras.size(); i++) {
			Game.activeCamera.update();
		}

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
