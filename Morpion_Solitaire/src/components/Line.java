package components;
import constants.Direction;
import constants.Status;

import java.util.List;
import java.util.ArrayList;

public class Line {
	
	private List<Point> points;
	
	private Point playedPoint;
	
	private Direction direction;
	
	public Line(Point playedPoint, Direction direction) {
		this.playedPoint = playedPoint;
		this.direction = direction;
		this.points = new ArrayList<>();
		points.add(playedPoint);
		
		for (int number = 0; number < 4; number ++) {
			points.add(
					direction == Direction.HORIZONTAL ? new Point(playedPoint.getX() + number, playedPoint.getY(), Status.LINE) : 
	                direction == Direction.VERTICAL ? new Point(playedPoint.getX(), playedPoint.getY() + number, Status.LINE) :
	                direction == Direction.DIAGONAL ? new Point(playedPoint.getX() + number, playedPoint.getY() + number, Status.LINE) :
	                null
			);
		}
		
	};
	
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
