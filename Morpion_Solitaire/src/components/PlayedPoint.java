package components;

import helpers.DefaultCoordinates;
import helpers.Direction;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PlayedPoint extends Point{
	
	/**
	 * Total number of played points
	 */
	private static int playedPointsCount;
	
	/**
	 * Id represent the order in which the current point was played
	 */
	private int id;
	
	
	/**
	 * The different direction of the lines that contain this played point
	 * This attribute is essential to know for searching point
	 */
	private Set<Direction> involvedInDirections;
	
	
	/**
	 * This attribute indicates if the current played point is the end of a line
	 * ex: the line from (0,0), (1,0), (2,0), (3,0)(4,0)
	 * 		(0,0) and (4,0) are the ends of the line
	 */
	private boolean isEndOfLine;
	
	public PlayedPoint(int x, int y) {
		super(x, y);
		if (!(DefaultCoordinates.getValues().contains(Objects.hash(x, y)))) {
			playedPointsCount += 1;
			this.id = playedPointsCount;
		}
		this.involvedInDirections = new HashSet<>();
		this.isEndOfLine = false;
	}
	
	public PlayedPoint(Point p) {
		super(p.getX(), p.getY());
		playedPointsCount += 1;
		this.id = playedPointsCount;
		this.involvedInDirections = new HashSet<>();
		this.isEndOfLine = false;
	}
	
	@Override
	public String toString() {
		return "Played point: (" + this.getX() + "," + this.getY() + ")";
	}
	
	public static void resetCount() {
		playedPointsCount = 0;
	}
	
	public int getId() {
		return this.id;
	}
	
	public static int getCount() {
		return playedPointsCount;
	}
	
	public Set<Direction> getInvolvedDirections(){
		return this.involvedInDirections;
	}
	
	public void addInvolvedDirection(Direction direction) {
		if (direction == null) throw new IllegalArgumentException("A valid direction must be called.");
		this.involvedInDirections.add(direction);
	}
	
	public boolean isEndOfLine() {
		return this.isEndOfLine;
	}
	
	public void setEndOfLine(boolean isEndOfLine) {
		this.isEndOfLine = isEndOfLine;
	}
	
	public static void main(String[] args) {
		PlayedPoint p = new PlayedPoint(0, 0);
		p.addInvolvedDirection(Direction.DIAGONAL1);
		System.out.println(p.getInvolvedDirections());
	}
	
	
}
