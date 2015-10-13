package me.nimnon.nmengine;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import me.nimnon.nmengine.core.GameThread;
import me.nimnon.nmengine.core.KeyboardListener;
import me.nimnon.nmengine.core.MousePadListener;
import me.nimnon.nmengine.entity.Basic;
import me.nimnon.nmengine.state.State;

/**
 * The heart of all NmEngine games, this handles the window and state, this
 * class should be instanced to create a game.
 * 
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
	 * Creates a new Game class, it is advised you only create one of these,
	 * else weird things happen.
	 * 
	 * @param width
	 *            Game width in pixels
	 * @param height
	 *            Game height in pixels
	 * @param title
	 *            Window Title
	 * @param state
	 *            Game starting state, if null is treated as a new State()
	 * @param tps
	 *            Ticks per second, how many times a second the update method is
	 *            called.
	 */
	public Game(int width, int height, String title, State state, int tps) {
		createGame(width, height, title, state, tps);
	}

	public Game(int width, int height, String title, State state) {
		createGame(width, height, title, state, 60);
	}

	/**
	 * Creates game window of specified width, height and title.
	 * 
	 * @param width
	 * @param height
	 * @param title
	 */
	public Game(int width, int height, String title) {
		createGame(width, height, title, new State(), 60);
	}

	/**
	 * Creates game window of specified width and height.
	 * 
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
	 * 
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
	 * 
	 * @param state
	 *            Which state to switch to.<code> null </code> will be treated
	 *            as a new State()
	 */

	public static void switchState(State state) {
		if (state == null)
			state = new State();
		if (currentState != null)
			currentState.destroy();
		currentState = state;
		state.create();
	}

	public static boolean overlap(Basic object1, Basic object2) {
		if (object1.x + object1.width - 1 >= object2.x && object1.x <= object2.x + object2.width) {
			if (object1.y + object1.height - 1 >= object2.y && object1.y <= object2.y + object2.height) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Collides object1 and object2, returns true if they collided successfully.
	 * HEAVILY BORROWS FROM ADAM ATOMICS FLIXEL COLLISION
	 * <a href="https://github.com/AdamAtomic/flixel/blob/master/org/flixel/FlxObject.as">HERE</a>
	 * 
	 * @param object1
	 * @param object2
	 * @return
	 */
	public static boolean collide(Basic object1, Basic object2) {
		return separateX(object1,object2) || separateY(object1,object2);
	}
	
	public static boolean separateX(Basic object1, Basic object2) {
		float overlap = 0;
		
		float obj1delta = object1.x - object1.last.x;
		float obj2delta = object2.x - object2.last.x;
		
		if(obj1delta != obj2delta)
		{
			float obj1deltaAbs = Math.abs(obj1delta);
			float obj2deltaAbs = Math.abs(obj2delta);
			
			Basic obj1rect = new Basic(object1.x+obj1delta , object1.last.y , object1.width+(int)obj1delta , object1.height);
			Basic obj2rect = new Basic(object2.x+obj2delta , object2.last.y , object2.width+(int)obj2delta , object2.height);
			
			if((obj1rect.x + obj1rect.width > obj2rect.x) && (obj1rect.x < obj2rect.x + obj2rect.width) && (obj1rect.y + obj1rect.height > obj2rect.y) && (obj1rect.y < obj2rect.y + obj2rect.height))
			{
				float maxOverlap = obj1deltaAbs + obj2deltaAbs + 4;
				
				if(obj1delta > obj2delta)
				{
					overlap = object1.x + (object1.width - object2.x);
				}
				else if (obj1delta < obj2delta)
				{
					overlap = (object1.x - object2.width) - object2.x;
				}
			}
			
			if(overlap != 0)
			{
				float obj1v = object1.velocity.x;
				float obj2v = object2.velocity.x;
				
				overlap *= 1;
				object1.x = object1.x - overlap;
				object1.velocity.x = 0;
				return true;
			}
		}
		return false;
	}

	public static boolean separateY(Basic object1, Basic object2) {
		float overlap = 0;
		
		float obj1delta = object1.y - object1.last.y;
		float obj2delta = object2.y - object2.last.y;
		
		if(obj1delta != obj2delta)
		{
			float obj1deltaAbs = Math.abs(obj1delta);
			float obj2deltaAbs = Math.abs(obj2delta);
			
			Basic obj1rect = new Basic(object1.x,object1.y-((obj1delta > 0)?obj1delta:0),object1.width,object1.height+(int)obj1delta);
			Basic obj2rect = new Basic(object2.x,object2.y-((obj2delta > 0)?obj2delta:0),object2.width,object2.height+(int)obj2delta);
			
			if((obj1rect.y + obj1rect.height > obj2rect.y) && (obj1rect.y < obj2rect.y + obj2rect.height) && (obj1rect.x + obj1rect.width > obj2rect.x) && (obj1rect.x < obj2rect.x + obj2rect.width))
			{
				float maxOverlap = obj1deltaAbs + obj2deltaAbs + 4;
				
				if(obj1delta > obj2delta)
				{
					overlap = object1.y + object1.height - object2.y;
				}
				else if (obj1delta < obj2delta)
				{
					overlap = object1.y - object2.height - object2.y;
				}
			}
			
			if(overlap != 0)
			{
				float obj1v = object1.velocity.y;
				float obj2v = object2.velocity.y;
				
				overlap *= 1;
				object1.y = object1.y - overlap;
				object1.velocity.y = 0;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if key was just pressed.
	 * 
	 * @param charCode
	 * @return if character was just pressed
	 */
	public static boolean getKeyJustPressed(int charCode) {
		if (keyListener.keysJustPressed[charCode]) {
			keyListener.keysJustPressed[charCode] = false;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns true if key is pressed down.
	 * 
	 * @param charCode
	 * @return if character is down
	 */
	public static boolean getKeyPressed(int charCode) {
		if (keyListener.keys[charCode])
			return true;
		else
			return false;
	}
}
