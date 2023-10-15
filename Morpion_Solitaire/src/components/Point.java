package components;

import constants.Status;

public class Point {
	
	private static int count;
	
	private int id;
	
	private Status status;
	
	/**
	 * Coordinates
	 */
	private int x, y;
		
	public Point(int x, int y, Status status) {
		this.x = x;
		this.y = y;
		this.status = status;
		this.count += 1;
		this.id = this.count;
	}
	
	public Status getStatus() {
		return this.status;
	}
	
	public int getId() {
		return this.id;
	}

	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}
