package me.nimnon.nmengine.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import me.nimnon.nmengine.Game;

public class MousePadListener implements MouseListener, MouseMotionListener {

	public MousePadListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Game.mouse.x = e.getX();
		Game.mouse.y = e.getY();

		Game.mouse.xWorld = (e.getX() / Game.activeCamera.zoom) + Game.activeCamera.x;
		Game.mouse.yWorld = (e.getY() / Game.activeCamera.zoom) + Game.activeCamera.y;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Game.mouse.x = e.getX();
		Game.mouse.y = e.getY();

		Game.mouse.xWorld = (e.getX() / Game.activeCamera.zoom) + Game.activeCamera.x;
		Game.mouse.yWorld = (e.getY() / Game.activeCamera.zoom) + Game.activeCamera.y;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			Game.mouse.mouse1Down = true;
		if (e.getButton() == MouseEvent.BUTTON2)
			Game.mouse.mouse2Down = true;
		if (e.getButton() == MouseEvent.BUTTON3)
			Game.mouse.mouse3Down = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			Game.mouse.mouse1Down = false;
		if (e.getButton() == MouseEvent.BUTTON2)
			Game.mouse.mouse2Down = false;
		if (e.getButton() == MouseEvent.BUTTON3)
			Game.mouse.mouse3Down = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
