package components;

public class Point {
	
	/**
	 * Coordinates
	 */
	private int x;
	private int y;
	
	private boolean isPlayable;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
