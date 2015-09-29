package me.nimnon.nmengine.core;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import me.nimnon.nmengine.Game;

public class GameThread extends JPanel implements Runnable {

	/**
	 * Contains Update and Graphics loop
	 */
	private static final long serialVersionUID = 1L;
	private boolean running = false;
	public int tps = 60;

	/**
	 * Called when the GameThread starts.
	 */
	public void run() {
		running  = true;
		gameLoop();
	}
	
	/**
	 * Method that starts the game loop.
	 */
	private void gameLoop()
	{
		long now = System.nanoTime();
		long last = System.nanoTime();
		long ns = 1000000000;
		long delta = 0;
		
		while(running)
		{
			now = System.nanoTime();
			delta += now-last;
			
			if(delta > ns/tps)
			{
				update();
				delta -= ns/tps;
			}
			repaint();
			last = now;
		}
	}
	/**
	 * Called directly from the game loop, calls update on the state which in turn updates everything else.
	 */
	private void update()
	{
		Game.currentState.update();
		tps = Game.ticksPerSecond;
	}
	
	/**
	 * Redraws the window.
	 */
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g;
		Game.currentState.draw(g2d);
	}

}
