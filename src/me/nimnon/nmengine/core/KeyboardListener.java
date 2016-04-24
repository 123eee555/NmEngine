package me.nimnon.nmengine.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import me.nimnon.nmengine.Game;

public class KeyboardListener implements KeyListener {


	@Override
	public void keyPressed(KeyEvent e) {
		try
		{
			if(!Game.keys.keys[e.getKeyCode()])
			{
				Game.keys.keysJustPressed[e.getKeyCode()] = true;
			}
			Game.keys.keys[e.getKeyCode()] = true;
		} 
		catch(Exception e2)
		{
			e2.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Game.keys.keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
