package components;

import java.util.Objects;

import helpers.exceptions.OutOfGridException;

public class Point {
	
	protected int x, y;

	public Point(int x, int y) {
		if (x < 0 || y < 0) throw new OutOfGridException("Coordinates cannot be negative.");
		if (x >= Grid.getSize() || y >= Grid.getSize()) throw new OutOfGridException("The point is outside the grid.");
		this.x = x;
		this.y = y;
	}
	
	public Point(Point p) {
		this.x = p.getX();
		this.y = p.getY();
	}
	
	
	/**
	 * Check if the point is in the grid
	 */
	public void checkRange() {
        if (x < 0 || y < 0) throw new OutOfGridException("Coordinates cannot be negative.");
        if (x >= 24 || y >= 24) throw new OutOfGridException("The point is outside the grid.");
    }
	
	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
	
	@Override
    public boolean equals(Object o) {
		if (this == o) return true;
	    if (o == null) return false;
	    if (o instanceof PlayedPoint) {
	        PlayedPoint otherPoint = (PlayedPoint) o;
	        return x == otherPoint.x && y == otherPoint.y;
	    }
	    if (o instanceof Point) {
	        Point otherPoint = (Point) o;
	        return x == otherPoint.x && y == otherPoint.y;
	    }
	    return false;
	}
    
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
    public boolean isPlayed() {
		return (this instanceof PlayedPoint);
	}
    
    public int getX() {
    	return this.x;
    }

    public int getY() {
    	return this.y;
    }
}
