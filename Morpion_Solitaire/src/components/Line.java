package components;

import java.util.Set;
import helpers.Direction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public class Line {
	
	/**
	 * Points of the line
	 */
	private Set<Point> points;
	
	/**
	 * The direction of the line
	 * 	1) Horizontal
	 * 	2) Vertical
	 * 	3) Diagonal
	 */
	private Direction direction;
	
	public Line(Set<Point> points, Direction direction) throws IllegalArgumentException {
		if (points.size() != 5) throw new IllegalArgumentException("A line should take a set of 5 points in argument.");
		if (direction == null) throw new IllegalArgumentException("A line must be defined with a direction.");
		this.direction = direction;
		this.points = points;
	};

	@Override
	public String toString() {
		String toString = "";
		for (Point point: this.points) {
				toString += point.toString();	
		}
		return toString;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Line)) return false;
        Line otherLine = (Line) o;
        System.out.println(this.points.equals(otherLine.getPoints()));
        return this.points.equals(otherLine.getPoints());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.points.toArray());
    }
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public Set<Point> getPoints(){
		return points;
	}
	
	public void updatePlayedPoint(PlayedPoint point) {
		if (point == null) throw new IllegalArgumentException ("Point cannot be null.");
		if (!point.isPlayed()) throw new IllegalArgumentException ("Must be a played point");
		this.points.remove(point);
		this.points.add(point);
	}
}
