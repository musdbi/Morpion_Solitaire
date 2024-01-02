package components;


import helpers.Direction;

import java.util.ArrayList;
import java.util.Set;

import game.Mode;

import java.util.HashSet;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
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
	 * 
	 * see {@link helpers.Direction}
	 */
	private Direction direction;
	
	public Line(Set<Point> points, Direction direction) throws IllegalArgumentException {
		if (Mode.getNumber() == 4) {
			if (points.size() != 4) throw new IllegalArgumentException("A line should take a set of 4 points in argument.");
		}
		if (Mode.getNumber() == 5) {
			if (points.size() != 5) throw new IllegalArgumentException("A line should take a set of 5 points in argument.");
		}
		if (direction == null) throw new IllegalArgumentException("A line must be defined with a direction.");
		this.direction = direction;
		this.points = new HashSet<Point>(points);
	}
	
	
	/**
	 * Constructor for defensive copy
	 * 
	 * @param line
	 */
	public Line(Line line) {
		this.points = new HashSet<Point>();
		for (Point linePoint: line.getPoints()) {
			if (linePoint.isPlayed()) {
				PlayedPoint linePointCopy = (PlayedPoint) linePoint;
				this.points.add(new PlayedPoint(linePointCopy));
			}
			else this.points.add(new Point(linePoint));
		}
		this.direction = line.getDirection();
	}

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
        return this.points.equals(otherLine.getPoints()) && this.direction == otherLine.getDirection();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.points.toArray());
    }
    
    
    /**
     * Update the Point instance in a PlayedPoint instance when the line is played
     * 
     * @param point
     */
    public void updatePlayedPoint(PlayedPoint point) {
		if (point == null) throw new IllegalArgumentException ("Point cannot be null.");
		if (!point.isPlayed()) throw new IllegalArgumentException ("Must be a played point");
		this.points.remove(point);
		this.points.add(point);
	}
    
    /**
     * @return the extremities of the line
     */
    public Set<Point> getEndsOfLine(){
    	Set<Point> endsOfLine = new HashSet<>();
    	 List<Point> points = new ArrayList<>(this.points);
    	 if (
    			 this.direction == Direction.DIAGONAL1 ||
    			 this.direction == Direction.DIAGONAL2 ||
    			 this.direction == Direction.HORIZONTAL
    		) {
    	        Comparator<Point> comparator = Comparator.comparingInt(Point::getX);
    	        Collections.sort(points, comparator);
    	 }
    	 else {
    		 Comparator<Point> comparator = Comparator.comparingInt(Point::getY);
 	        Collections.sort(points, comparator);
    	 }
    	 endsOfLine.add(points.get(0));
    	 endsOfLine.add(points.get(points.size() - 1));
    	 return endsOfLine;
    }
    
	public Direction getDirection() {
		return this.direction;
	}
	
	public Set<Point> getPoints(){
		return points;
	}
}
