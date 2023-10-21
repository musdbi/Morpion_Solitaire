package components;

import java.awt.Point;

import constants.Direction;

public class PointUtils {
	
	/**
	 * This method helps to identify the direction from one point to another
	 * 
	 * @param p1
	 * @param p2
	 * @return Direction from p1 to p2. See {@link Direction} for precision on Direction values
	 */
	public static Direction getDirection(Point p1, Point p2) {
		return 
			((
				p2.getX() > p1.getX() && p2.getY() > p1.getY() ? Direction.NE :
				p2.getX() > p1.getX() && p2.getY() == p1.getY() ? Direction.E :
				p2.getX() > p1.getX() && p2.getY() < p1.getY() ? Direction.SE :
				p2.getX() == p1.getX() && p2.getY() < p1.getY() ? Direction.S :
				p2.getX() < p1.getX() && p2.getY() < p1.getY() ? Direction.SW :
				p2.getX() < p1.getX() && p2.getY() == p1.getY() ? Direction.W :
				p2.getX() < p1.getX() && p2.getY() > p1.getY() ? Direction.NW :
				p2.getX() == p1.getX() && p2.getY() > p1.getY() ? Direction.N :
				null
			));		
	}
	
	/** 
	 *
	 * @param p1
	 * @param p2
	 * @return The infinite distance in 2-dimension
	 */
	public static int distance(Point p1, Point p2) {
		return (int) Math.max(Math.abs(p1.getX() - p2.getX()), Math.abs(p1.getY() - p2.getY()));
	}
}
