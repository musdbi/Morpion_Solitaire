package components;

import java.util.Objects;

import constants.Direction;

public class Point {
	
	protected int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(PlayedPoint p) {
		this.x = p.getX();
		this.y = p.getY();
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
    
    public void move(int x, int y) {
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
