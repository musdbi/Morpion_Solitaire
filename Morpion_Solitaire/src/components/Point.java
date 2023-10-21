package components;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import constants.Direction;

public class Point {
	
	private int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
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
	
	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Point)) return false;
        Point otherPoint = (Point) o;
        return x == otherPoint.x && y == otherPoint.y;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
    public int getX() {
    	return this.x;
    }

    public int getY() {
    	return this.y;
    }
    
    public static void main(String[] args) {
        Set<Point> set = new HashSet<>();

        Point p1 = new Point(0, 0);
        set.add(p1);
        System.out.println(set);

        Point p2 = new Point(0, 0);
        set.add(p2);
        System.out.println(set);
    }
}
