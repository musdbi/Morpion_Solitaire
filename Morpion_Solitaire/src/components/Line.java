package components;

import constants.Direction;
import constants.Status;
import java.util.Set;
import java.util.HashSet;

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
	
	public Line(Set<Point> points, Direction direction) {
		this.direction = direction;
		this.points = new HashSet<>();
		this.points.addAll(points);
	};
	
	public void addPoint(Point point) {
		this.points.add(point);
	}
	@Override
	public String toString() {
		String toString = "Direction: " + this.direction + "\nPoints:";
		for (Point point: this.points) {
			toString += " " + point.toString(); 
		}
		return toString;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public Set<Point> getPoints(){
		return points;
	}
}
