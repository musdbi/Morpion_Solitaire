package components;

import helpers.OutOfGridException;
import java.util.Objects;

public class Point {
	
	protected int x, y;

	public Point(int x, int y) {
		if (x < 0 || y < 0) throw new OutOfGridException("Coordinates cannot be negative.");
		if (x >= 24 || y >= 24) throw new OutOfGridException("The point is outside the grid.");
		this.x = x;
		this.y = y;
	}
	
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
    
    public void move(int x, int y) {
    	if (x < 0 || y < 0) throw new OutOfGridException("Coordinates cannot be negative.");
		if (x >= 24 || y >= 24) throw new OutOfGridException("The point is outside the grid.");
    	this.x = x;
    	this.y = y;
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
    
    public static void main(String[] args) {
    	
    }
}
