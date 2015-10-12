package me.nimnon.nmengine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import me.nimnon.nmengine.core.GameThread;
import me.nimnon.nmengine.core.KeyboardListener;
import me.nimnon.nmengine.core.MousePadListener;
import me.nimnon.nmengine.entity.Basic;
import me.nimnon.nmengine.entity.GameObject;
import me.nimnon.nmengine.state.State;

/**
 * The heart of all NmEngine games, this handles the window and state, this class should be instanced to create a game.
 * @author Nimnon
 * 
 */

public class Game {
	
	private JFrame window;
	private GameThread thread;
	
	private static KeyboardListener keyListener;
	private MousePadListener mouseListener;
	
	public static int ticksPerSecond = 60;
	
	public static Mouse mouse = new Mouse();
	
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
		keyListener = new KeyboardListener();
		
		window = new JFrame(title);
		thread = new GameThread();
		
		window.setTitle(title);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setFocusable(true);
		
		thread.setPreferredSize(new Dimension(width, height));
		thread.setBackground(Color.WHITE);
		thread.setFocusable(true);
		thread.requestFocus();
		
		window.add(thread);
		
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		thread.addMouseListener(mouseListener);
		thread.addMouseMotionListener(mouseListener);
		thread.addKeyListener(keyListener);
		
		thread.requestFocus();
		
		
		
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
	
	public static boolean overlap(Basic object1, Basic object2)
	{
		if(object1.x+object1.width-1 >= object2.x && object1.x <= object2.x+object2.width) {
			if(object1.y+object1.height-1 >= object2.y && object1.y <= object2.y+object2.height) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Collides object1 and object2, returns true if they collided successfully.
	 * @param object1
	 * @param object2
	 * @return
	 */
	public static boolean collide(GameObject object1, GameObject object2)
	{
		if(overlap(object1, object2))
		{
			
			float d = object1.y - (object2.y + object2.height);
			float u = (object1.y + object1.height) - object2.y;
			float r = object1.x - (object2.x + object2.width);
			float l = (object1.x + object1.width) - object2.x;
			
			float deltaY = Math.abs(u) > Math.abs(d) ? d : u;
			float deltaX = Math.abs(l) > Math.abs(r) ? r : l;
			
			double timeToDeltaY = (object1.next.y - object1.y) / deltaY;
			double timeToDeltaX = (object1.next.x - object1.x) / deltaX;
			
			if(Math.abs(timeToDeltaX) <= Math.abs(timeToDeltaY))
			{
				object1.y -= deltaY;
				object1.velocity.y = 0;
			}
			else
			{
				object1.x -= deltaX;
				object1.velocity.x = 0;
			}
			return true;
		}
		return false;
	}
	

	
	/**
	 * Returns true if key was just pressed.
	 * @param charCode
	 * @return if character was just pressed
	 */
	public static boolean getKeyJustPressed(int charCode)
	{
		if(keyListener.keysJustPressed[charCode])
		{
			keyListener.keysJustPressed[charCode] = false;
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	 * Returns true if key is pressed down.
	 * @param charCode
	 * @return if character is down
	 */
	public static boolean getKeyPressed(int charCode)
	{
		if(keyListener.keys[charCode])
			return true;
		else
			return false;
	}
}
