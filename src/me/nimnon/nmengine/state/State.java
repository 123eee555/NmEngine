package me.nimnon.nmengine.state;

import me.nimnon.nmengine.Game;
import me.nimnon.nmengine.entity.Group;

/**
 * State class, each of these are a scene that can be switched to and from.
 * 
 * @author Nimnon
 *
 */

public class State extends Group {

	/**
	 * Creates state
	 */
	public State() {

	}
	
	/**
	 * Clears children then switches to a plain State
	 */
	public void destroy() {
		this.children.clear();
		this.children = null;
		Game.switchState(new State());
	}

}
