package components;
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
	 * The point that created the line
	 */
	private Point playedPoint;
	
	/**
	 * The direction of the line
	 * 	1) Horizontal
	 * 	2) Vertical
	 * 	3) Diagonal
	 */
	private Direction direction;
	
	public Line(
			Point playedPoint,
//			List<Point> points,
			Direction direction
		) {
		this.playedPoint = playedPoint;
		this.direction = direction;
		this.points = new ArrayList<>();
//		this.points.addAll(points);
		points.add(playedPoint);
		
		for (int i = 0; i < 4; i ++) {
			points.add(
					direction == Direction.HORIZONTAL ? new Point(playedPoint.getX() + i, playedPoint.getY(), Status.LINE) : 
	                direction == Direction.VERTICAL ? new Point(playedPoint.getX(), playedPoint.getY() + i, Status.LINE) :
	                direction == Direction.DIAGONAL ? new Point(playedPoint.getX() + i, playedPoint.getY() + i, Status.LINE) :
	                null
			);
		}
		
	};
	
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
