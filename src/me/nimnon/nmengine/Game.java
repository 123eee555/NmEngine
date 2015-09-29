package me.nimnon.nmengine;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import me.nimnon.nmengine.core.GameThread;
import me.nimnon.nmengine.state.State;

/**
 * The heart of all NmEngine games, this handles the window and state, this class should be instanced to create a game.
 * @author Nimnon
 * 
 */

public class Game {
	
	private JFrame window;
	private GameThread thread;
	
	public static int ticksPerSecond = 60;
	
	public static State currentState;

	/**
	 * Creates a new Game class, it is advised you only create one of these, else weird things happen.
	 * 
	 * @param width
	 * 		Game width in pixels
	 * @param height
	 * 		Game height in pixels
	 * @param title
	 * 		Window Title
	 * @param state
	 * 		Game starting state, if null is treated as a new State()
	 */
	
	public Game(int width, int height, String title, State state, int tps) {
		if (state == null)
			state = new State();
		ticksPerSecond = tps;
		
		window = new JFrame(title);
		thread = new GameThread();
		
		window.setTitle(title);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		thread.setPreferredSize(new Dimension(width, height));
		thread.setBackground(Color.WHITE);
		
		window.add(thread);
		
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		switchState(state);
		
		thread.tps = ticksPerSecond;
		new Thread(thread).start();
		
		
	}
	
	/**
	 * Method to switch from state to state
	 * @param state
	 * 		Which state to switch to.<code> null </code> will be treated as a new State()
	 */
	
	public static void switchState(State state)
	{
		if(state==null)
			state = new State();
		if(currentState != null)
			currentState.destroy();
		currentState = state;
	}

}
