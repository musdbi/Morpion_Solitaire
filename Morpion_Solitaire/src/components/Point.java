package components;

import constants.Status;

public class Point {
	
	
	/**
	 * Total number of played points
	 */
	private static int playedPointsCount;
	
	/**
	 * Id represent the order in which the current point was played
	 */
	private int id;
	
	/**
	 * Represent the status of a point in different moment of the game
	 * 4 status are possible:
	 * 	1) PLAYED: different points clicked by the player
	 * 	2) DEFAULT: points that constitute the cross at the beginning of the game
	 * 	3) LINE: points that are part of a line
	 * 	4) NORMAL: points that are not one of the 3 previous type
	 */
	private Status status;
	
	/**
	 * Coordinates
	 */
	private int x, y;
		
	public Point(int x, int y, Status status) {
		this.x = x;
		this.y = y;
		this.status = status;
		if (this.status == Status.PLAYED) {
			playedPointsCount += 1;
			this.id = playedPointsCount;
		}
	}
	
	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
	
	/**
	 * Set status and give an id if this a played point
	 * 
	 * @param status
	 */
	public void setStatus(Status status) {
		if (status == Status.PLAYED) {
			playedPointsCount += 1;
			this.id = playedPointsCount;
		}
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
	
	public static int getPlayedPointsCount() {
		return playedPointsCount;
	}
}
