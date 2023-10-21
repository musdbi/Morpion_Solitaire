package components;

import java.awt.Point;
import constants.Direction;
import constants.Status;
import java.util.List;
import java.util.ArrayList;

public class Line {
	
	/**
	 * Points of the line
	 */
	private List<Point> points;
	
	/**
	 * The direction of the line
	 * 	1) Horizontal
	 * 	2) Vertical
	 * 	3) Diagonal
	 */
	private Direction direction;
	
	public Line(Direction direction) {
		this.direction = direction;
		this.points = new ArrayList<>();
		this.points.addAll(points);
		points.add(playedPoint);
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
	
	public List<Point> getPoints(){
		return points;
	}
	
	public Point getPlayedPoint(){
		return playedPoint;
	}
}
