package me.nimnon.nmengine.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

	public boolean[] keys;
	public boolean[] keysJustPressed;
	
	public KeyboardListener() {
		keys = new boolean[1024];
		keysJustPressed = new boolean[1024];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		try
		{
			if(!keys[e.getKeyCode()])
			{
				keysJustPressed[e.getKeyCode()] = true;
			}
			keys[e.getKeyCode()] = true;
		} 
		catch(Exception e2)
		{
			e2.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
