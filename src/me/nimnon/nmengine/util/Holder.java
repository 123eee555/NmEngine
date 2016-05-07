package me.nimnon.nmengine.util;

import java.util.ArrayList;

import me.nimnon.nmengine.entity.Basic;


public interface Holder<T extends Basic> {
	public ArrayList<T> getChildren();
	public void add(T object);
	public void remove(T object);
}