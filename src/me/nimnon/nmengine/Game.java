package me.nimnon.nmengine;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import me.nimnon.nmengine.core.GameThread;
import me.nimnon.nmengine.core.KeyboardListener;
import me.nimnon.nmengine.core.MousePadListener;
import me.nimnon.nmengine.entity.Camera;
import me.nimnon.nmengine.entity.GameObject;
import me.nimnon.nmengine.util.Rect;
import me.nimnon.nmengine.state.State;

/**
 * The heart of all NmEngine games, this handles the window and state, this
 * class should be instanced to create a game.
 * 
 * @author Nimnon
 * 
 */

public class Game {

	/**
	 * Actual window
	 */
	private JFrame window;
	
	/**
	 * GameThread component housed in the window, controls update and render loop
	 */
	private GameThread thread;

	/**
	 * Keyboard Listener that takes keyboard inputs
	 */
	private static KeyboardListener keyListener;
	/**
	 * Mouse Listener that takes mouse inputs and movements
	 */
	private MousePadListener mouseListener;

	/**
	 * Game updates per second
	 */
	public static int ticksPerSecond = 60;

	/**
	 * Static Mouse instance for getting mouse Properties
	 */
	public static Mouse mouse = new Mouse();

	/**
	 * Current focused camera
	 */
	public static Camera activeCamera;
	
	/**
	 * List of total cameras
	 */
	public static ArrayList<Camera> cameras = new ArrayList<Camera>();

	public static Color backgroundColor = Color.white;
	
	/**
	 * Active state
	 */
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
	 *            Window title
	 * @param state
	 *            Game starting state, if null is treated as a new State()
	 * @param tps
	 *            Ticks per second, how many times a second the update method is
	 *            called.
	 * @param backgroundColor
	 *            Background color of the application.
	 */
	public Game(int width, int height, String title, State state, int tps, Color backgroundColor) {
		createGame(width, height, title, state, tps, backgroundColor);
	}

	/**
	 * Creates a new Game class, it is advised you only create one of these,
	 * else weird things happen.
	 * 
	 * @param width
	 *            Game width in pixels
	 * @param height
	 *            Game height in pixels
	 * @param title
	 *            Window title
	 * @param state
	 *            Game starting state
	 * @param tps
	 *            Ticks per second, how many times a second the update method is
	 *            called.
	 */
	public Game(int width, int height, String title, State state, int tps) {
		createGame(width, height, title, state, tps, Color.white);
	}

	/**
	 * Creates a new game with specified width, height, title, and state, the background color is white
	 * @param width
	 * @param height
	 * @param title
	 * @param state
	 */
	public Game(int width, int height, String title, State state) {
		createGame(width, height, title, state, 60, Color.white);
	}

	/**
	 * Creates a 400x300px window with the specified state and title
	 * 
	 * @param title
	 *            Window title * @param state Game starting state
	 */
	public Game(String title, State state) {
		createGame(400, 300, title, state, 60, Color.white);
	}

	/**
	 * Creates game window of specified width, height and title.
	 * 
	 * @param width
	 *            Game width in pixels
	 * @param height
	 *            Game height in pixels
	 * @param title
	 *            Window title
	 * 
	 */
	public Game(int width, int height, String title) {
		createGame(width, height, title, new State(), 60, Color.white);
	}

	/**
	 * Creates game window of specified width and height.
	 * 
	 * @param width
	 *            Game width in pixels
	 * @param height
	 *            Game height in pixels
	 */
	public Game(int width, int height) {
		createGame(width, height, "Game", new State(), 60, Color.white);
	}

	/**
	 * Creates empty game window of a blank state
	 */
	public Game() {
		createGame(400, 300, "Game", new State(), 60, Color.white);
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
	private void createGame(int width, int height, String title, State state, int tps, Color backgroundColor) {
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
		window.setResizable(false);

		thread.setPreferredSize(new Dimension(width, height));
		Game.backgroundColor = backgroundColor;
		thread.setBackground(backgroundColor);
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
		
		activeCamera = new Camera(0, 0, width, height, 3);
		cameras.add(activeCamera);
		
		switchState(state);
		
		thread.tps = ticksPerSecond;
		new Thread(thread).start();

	}

	/**
	 * Switch from current state to a new state
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

	/**
	 * Returns true if objects intersect
	 * 
	 * @param object1
	 * @param object2
	 * @return boolean
	 */
	public static boolean overlap(GameObject object1, GameObject object2) {
		if (object1.x + object1.width - 1 >= object2.x && object1.x <= object2.x + object2.width) {
			if (object1.y + object1.height - 1 >= object2.y && object1.y <= object2.y + object2.height) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Collides object1 and object2, returns true if they collided successfully.
	 * HEAVILY BORROWS FROM ADAM ATOMICS FLIXEL COLLISION <a href=
	 * "https://github.com/AdamAtomic/flixel/blob/master/org/flixel/FlxObject.as">
	 * HERE IS THE SOURCE</a>
	 * 
	 * @param object1
	 *            Object to collide
	 * @param object2
	 *            Other Object to collide
	 * @return boolean
	 */
	public static boolean collide(GameObject object1, GameObject object2) {
		return separateX(object1, object2) || separateY(object1, object2);
	}

	/**
	 * Separates along X axis, separation is based on direction object is
	 * traveling 99% of this method borrows from flixels separation code
	 * <a href=
	 * "https://github.com/AdamAtomic/flixel/blob/master/org/flixel/FlxObject.as">
	 * HERE IS THE SOURCE</a>
	 * 
	 * @param object1
	 * @param object2
	 * @return boolean
	 */
	public static boolean separateX(GameObject object1, GameObject object2) {
		double overlap = 0;

		double obj1delta = object1.x - object1.last.x;
		double obj2delta = object2.x - object2.last.x;

		if (obj1delta != obj2delta) {
			//double obj1deltaAbs = Math.abs(obj1delta);
			//double obj2deltaAbs = Math.abs(obj2delta);

			Rect obj1rect = new Rect(object1.x + obj1delta, object1.last.y, object1.width + obj1delta, object1.height);
			Rect obj2rect = new Rect(object2.x + obj2delta, object2.last.y, object2.width + obj2delta, object2.height);

			if ((obj1rect.x + obj1rect.width > obj2rect.x) && (obj1rect.x < obj2rect.x + obj2rect.width)
					&& (obj1rect.y + obj1rect.height > obj2rect.y) && (obj1rect.y < obj2rect.y + obj2rect.height)) {
				//double maxOverlap = obj1deltaAbs + obj2deltaAbs + 4;

				if (obj1delta > obj2delta) {
					object1.touching[3] = true;
					object2.touching[2] = true;
					overlap = object1.x + (object1.width - object2.x);
				} else if (obj1delta < obj2delta) {
					object1.touching[2] = true;
					object2.touching[3] = true;
					overlap = (object1.x - object2.width) - object2.x;
				}
			}

			if (overlap != 0) {
				double obj1v = object1.velocity.x;
				double obj2v = object2.velocity.x;

				object1.x = object1.x - overlap;
				object1.velocity.x = (obj1v - obj2v) * 0;
				return true;
			}
		}
		return false;
	}

	/**
	 * Separates along Y axis, separation is based on direction object is
	 * traveling 99% of this method borrows from flixels separation code
	 * <a href=
	 * "https://github.com/AdamAtomic/flixel/blob/master/org/flixel/FlxObject.as">
	 * HERE IS THE SOURCE</a>
	 * 
	 * @param object1
	 * @param object2
	 * @return boolean
	 */
	public static boolean separateY(GameObject object1, GameObject object2) {
		double overlap = 0;

		double obj1delta = object1.y - object1.last.y;
		double obj2delta = object2.y - object2.last.y;

		if (obj1delta != obj2delta) {
			//double obj1deltaAbs = Math.abs(obj1delta);
			//double obj2deltaAbs = Math.abs(obj2delta);

			Rect obj1rect = new Rect(object1.x, object1.y - ((obj1delta > 0) ? obj1delta : 0), object1.width,
					object1.height + obj1delta);
			Rect obj2rect = new Rect(object2.x, object2.y - ((obj2delta > 0) ? obj2delta : 0), object2.width,
					object2.height + obj2delta);

			if ((obj1rect.y + obj1rect.height > obj2rect.y) && (obj1rect.y < obj2rect.y + obj2rect.height)
					&& (obj1rect.x + obj1rect.width > obj2rect.x) && (obj1rect.x < obj2rect.x + obj2rect.width)) {
				//double maxOverlap = obj1deltaAbs + obj2deltaAbs + 4;

				if (obj1delta > obj2delta) {
					overlap = object1.y + object1.height - object2.y;
					object1.touching[0] = true;
					object2.touching[1] = true;

				} else if (obj1delta < obj2delta) {
					overlap = object1.y - object2.height - object2.y;
					object1.touching[1] = true;
					object2.touching[0] = true;
				}
			}

			if (overlap != 0) {
				double obj1v = object1.velocity.y;
				double obj2v = object2.velocity.y;

				object1.y = object1.y - overlap;
				object1.velocity.y = (obj2v - obj1v) * 0;
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
