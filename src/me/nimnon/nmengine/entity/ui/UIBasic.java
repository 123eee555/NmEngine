package me.nimnon.nmengine.entity.ui;

import java.awt.Rectangle;

import me.nimnon.nmengine.entity.GameObject;

public class UIBasic extends GameObject {
	public UIBasic() {
		super();
	}

	public UIBasic(double x, double y) {
		super(x, y);
	}

	public UIBasic(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	public UIBasic(Rectangle rect) {
		super(rect);
	}
}
