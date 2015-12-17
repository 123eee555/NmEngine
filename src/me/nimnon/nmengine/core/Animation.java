package me.nimnon.nmengine.core;

public class Animation {

	public String name;
	public double delay;
	public int[] frames;
	public boolean loops;

	public Animation() {
		name = "_";
		delay = 0;
		frames = new int[]{0};
		loops = false;
	}
	
	public Animation(String name, double framerate, int[] frames, boolean loops) {
		this.name = name;
		if(framerate > 0)
			this.delay = 1.0/framerate;
		this.frames = frames;
		this.loops = loops;
	}

}
