package me.nimnon.nmengine;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import me.nimnon.nmengine.core.GameThread;
import me.nimnon.nmengine.state.State;

public class Game {
	
	private JFrame window;
	private GameThread thread;
	
	public static State currentState;

	public Game(int width, int height, String title, State state) {
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
		
		new Thread(thread).start();
		
		
	}
	
	public static void switchState(State state)
	{
		if(currentState != null)
			currentState.destroy();
		currentState = state;
	}

}
