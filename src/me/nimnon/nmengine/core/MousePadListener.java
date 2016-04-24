package me.nimnon.nmengine.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import me.nimnon.nmengine.Game;

public class MousePadListener implements MouseListener, MouseMotionListener, MouseWheelListener {

	public MousePadListener() {

	}

	/**
	 * Called when the mouse is held down and moved
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		Game.mouse.setX((e.getX()));
		Game.mouse.setY((e.getY()));
	}

	/**
	 * Called when the mouse is moved and no buttons are pressed
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		Game.mouse.setX((e.getX()));
		Game.mouse.setY((e.getY()));
	}

	/**
	 * Called after the mouse presses down, then up
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	/**
	 * Called when the mouse is pressed down
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (!Game.mouse.getMouse1Down())
				Game.mouse.setMouse1JustPressed(true);
			Game.mouse.setMouse1Down(true);
		}
		if (e.getButton() == MouseEvent.BUTTON2) {
			if (!Game.mouse.getMouse2Down())
				Game.mouse.setMouse2JustPressed(true);
			Game.mouse.setMouse2Down(true);
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			if (!Game.mouse.getMouse3Down())
				Game.mouse.setMouse3JustPressed(true);
			Game.mouse.setMouse3Down(true);
		}
	}

	/**
	 * Called when the mouse is unpressed
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			Game.mouse.setMouse1Down(false);
			Game.mouse.setMouse3JustPressed(false);
		}
		if (e.getButton() == MouseEvent.BUTTON2) {
			Game.mouse.setMouse2Down(false);
			Game.mouse.setMouse2JustPressed(false);
		}
		if (e.getButton() == MouseEvent.BUTTON3) {
			Game.mouse.setMouse3Down(false);
			Game.mouse.setMouse3JustPressed(false);
		}
	}

	/**
	 * Called when the mouse enters parent component
	 */
	@Override
	public void mouseEntered(MouseEvent e) {

	}

	/**
	 * Called when the mouse leaves parent component
	 */
	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		Game.mouse.setMouseScroll(e.getWheelRotation());
	}

}
