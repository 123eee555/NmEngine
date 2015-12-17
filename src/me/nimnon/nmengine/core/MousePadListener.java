package me.nimnon.nmengine.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import me.nimnon.nmengine.Game;

public class MousePadListener implements MouseListener, MouseMotionListener {

	public MousePadListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Called when the mouse is held down and moved
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		Game.mouse.x = (e.getX() / Game.activeCamera.zoom);
		Game.mouse.y = (e.getY() / Game.activeCamera.zoom);

		Game.mouse.xWorld = (e.getX() / Game.activeCamera.zoom) + (Game.activeCamera.x);
		Game.mouse.yWorld = (e.getY() / Game.activeCamera.zoom) + (Game.activeCamera.y);
	}

	/**
	 * Called when the mouse is moved and no buttons are pressed
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		Game.mouse.x = (e.getX() / Game.activeCamera.zoom);
		Game.mouse.y = (e.getY() / Game.activeCamera.zoom);

	}

	/**
	 * Called after the mouse presses down, then up
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Called when the mouse is pressed down
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			Game.mouse.mouse1Down = true;
		if (e.getButton() == MouseEvent.BUTTON2)
			Game.mouse.mouse2Down = true;
		if (e.getButton() == MouseEvent.BUTTON3)
			Game.mouse.mouse3Down = true;
	}

	/**
	 * Called when the mouse is unpressed
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			Game.mouse.mouse1Down = false;
		if (e.getButton() == MouseEvent.BUTTON2)
			Game.mouse.mouse2Down = false;
		if (e.getButton() == MouseEvent.BUTTON3)
			Game.mouse.mouse3Down = false;
	}

	/**
	 * Called when the mouse enters parent component
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Called when the mouse leaves parent component
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
