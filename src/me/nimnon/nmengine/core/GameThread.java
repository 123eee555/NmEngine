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

	public GameThread() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		running  = true;
		gameLoop();
	}
	
	public void gameLoop()
	{
		long now = System.nanoTime();
		long last = System.nanoTime();
		long ns = 1000000000;
		long delta = 0;
		int tps = 60;
		
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
	
	public void update()
	{
		Game.currentState.update();
	}
	
	
	float[] hsv = new float[3];
	
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g;
		Game.currentState.draw(g2d);
	}

}
