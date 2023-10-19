package constants;

import java.awt.Point;

public enum Direction {
	N, // North
	S, // South
	W, // West
	E, // East
	NW, // North West
	NE, // North East
	SW, // South West
	SE; // South East
	
	/**
	 * This method helps to identify the direction from one point to another
	 * 
	 * @param p1
	 * @param p2
	 * @return Direction depending on p2 position according to p1 position. See {@link Direction} for precision on its values
	 */
	public static Direction getDirection(Point p1, Point p2) {
		return (
				(
					p2.getX() > p1.getX() && p2.getY() > p1.getY() ? Direction.NE :
					p2.getX() > p1.getX() && p2.getY() == p1.getY() ? Direction.E :
					p2.getX() > p1.getX() && p2.getY() < p1.getY() ? Direction.SE :
					p2.getX() == p1.getX() && p2.getY() < p1.getY() ? Direction.S :
					p2.getX() < p1.getX() && p2.getY() < p1.getY() ? Direction.SW :
					p2.getX() < p1.getX() && p2.getY() == p1.getY() ? Direction.W :
					p2.getX() < p1.getX() && p2.getY() > p1.getY() ? Direction.NW :
					p2.getX() == p1.getX() && p2.getY() > p1.getY() ? Direction.N :
					null
						)
				);		
	}
	
}
