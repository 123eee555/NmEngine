package me.nimnon.nmengine.util;

public class Vector2 {
	
	public double x;
	public double y;
	
	public Vector2() {
		x = 0;
		y = 0;
	}
	
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2 set(double x, double y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	public double length() {
		return Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
	}
	
	/**
	 * Returns normalized vector (Vector with length of 1)
	 * @return normalized vector
	 */
	public Vector2 unit() {
		double l = length();
		Vector2 newVec = new Vector2(x/l,y/l);
		return newVec;
	}
	
	/**
	 * Returns this vector minus c
	 * @param c Number to subtract
	 * @return difference
	 */
	public Vector2 diff(Double c) {
		Vector2 newVec = new Vector2(this.x - c,this.y -c);
		return newVec;
	}
	
	/**
	 * Returns this vector minus v2
	 * @param v2 vector to subtract
	 * @return difference
	 */
	public Vector2 diff(Vector2 v2) {
		Vector2 newVec = new Vector2(this.x - v2.x,this.y -v2.y);
		return newVec;
	}
	
	/**
	 * Returns this vector multiplied by C
	 * @param c Number to multiply
	 * @return product
	 */
	public Vector2 mult(Double c) {
		Vector2 newVec = new Vector2(this.x * c,this.y * c);
		return newVec;
	}
	
	/**
	 * Returns this vector multiplied by v2
	 * @param v2 vector to multiply
	 * @return product
	 */
	public Vector2 mult(Vector2 v2) {
		Vector2 newVec = new Vector2(this.x * v2.x,this.y * v2.y);
		return newVec;
	}
	
	/**
	 * Returns this vector shortened by s
	 * @param s
	 * @return clipped vector
	 */
	public Vector2 shortenLength(double s) 
	{
	    Vector2 v2 = this;
		v2 = v2.mult(1 - s/length());
	    return v2;
	}
}
