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
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Game.mouse.x = e.getX();
		Game.mouse.y = e.getY();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		Game.mouse.mouse1Down = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Game.mouse.mouse1Down = false;
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
