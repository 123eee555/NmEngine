package me.nimnon.nmengine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import me.nimnon.nmengine.core.GameThread;
import me.nimnon.nmengine.core.MousePadListener;
import me.nimnon.nmengine.state.State;

/**
 * The heart of all NmEngine games, this handles the window and state, this class should be instanced to create a game.
 * @author Nimnon
 * 
 */

public class Game {
	
	private JFrame window;
	private GameThread thread;
	private MousePadListener mouseListener;
	
	public static int ticksPerSecond = 60;
	
	public static Point mouse = new Point(0,0);
	
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
	 * @param tps
	 * 		Ticks per second, how many times a second the update method is called.
	 */
	public Game(int width, int height, String title, State state, int tps) {
		createGame(width, height, title, state, tps);
	}
	
	public Game(int width, int height, String title, State state) {
		createGame(width, height, title, state, 60);
	}
	
	/**
	 * Creates game window of specified width, height and title.
	 * @param width
	 * @param height
	 * @param title
	 */
	public Game(int width, int height, String title) {
		createGame(width, height, title, new State(), 60);
	}
	
	/**
	 * Creates game window of specified width and height.
	 * @param width
	 * @param height
	 */
	public Game(int width, int height) {
		createGame(width, height, "Game", new State(), 60);
	}
	
	/**
	 * Creates empty game window
	 */
	public Game() {
		createGame(400, 300, "Game", new State(), 60);
	}
	
	/**
	 * Called by constructor.
	 * @param width
	 * @param height
	 * @param title
	 * @param state
	 * @param tps
	 */
	private void createGame(int width, int height, String title, State state, int tps) {
		if (state == null)
			state = new State();
		ticksPerSecond = tps;
		
		mouseListener = new MousePadListener();
		
		window = new JFrame(title);
		thread = new GameThread();
		
		window.setTitle(title);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setFocusable(true);
		window.setMinimumSize(new Dimension(400, 300));
		
		thread.setPreferredSize(new Dimension(width, height));
		thread.setBackground(Color.WHITE);
		thread.setFocusable(true);
		
		window.add(thread);
		
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		thread.requestFocus();
		
		thread.addMouseListener(mouseListener);
		thread.addMouseMotionListener(mouseListener);
		
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
		state.create();
	}

}
