package components;

import helpers.Direction;
import java.util.HashSet;
import java.util.Set;

public class PlayedPoint extends Point{
	
	/**
	 * Id represent the order in which the current point was played
	 */
	private int id;
	
	
	/**
	 * The different direction of the lines that contain this played point
	 * This attribute is essential to know when searching playable points
	 */
	private Set<Direction> involvedInDirections;
	
	
	/**
	 * This attribute indicates if the current played point is the end of a line
	 * ex: the line from (0,0), (1,0), (2,0), (3,0)(4,0)
	 * 		(0,0) and (4,0) are the ends of the line
	 */
	private boolean isEndOfLine;
	
	
	/**
	 * Constructor for default coordinates
	 * 
	 * @param x
	 * @param y
	 */
	public PlayedPoint(int x, int y) {
		super(x, y);
		this.involvedInDirections = new HashSet<>();
		this.isEndOfLine = false;
	}
	
	
	/**
	 * Constructor when a point is played
	 * 
	 * @param p
	 * @param id
	 */
	public PlayedPoint(Point p, int id) {
		super(p.getX(), p.getY());
		this.involvedInDirections = new HashSet<>();
		this.isEndOfLine = false;
		this.id = id;
	}
	
	/**
	 * Constructor for defensive copies
	 * 
	 * @param playedPoint
	 */
	public PlayedPoint(PlayedPoint playedPoint) {
		super(playedPoint.getX(), playedPoint.getY());
		this.id = playedPoint.getId();
		this.involvedInDirections = new HashSet<>();
		for (Direction direction: playedPoint.getInvolvedDirections()) {
			this.involvedInDirections.add(direction);
		}
		this.isEndOfLine = playedPoint.isEndOfLine();
	}
	
	@Override
	public String toString() {
		return "Played point: (" + this.getX() + "," + this.getY() + ")";
	}
	
	public String toStringBis() {
		return "(" + this.getX() + "," + this.getY() + ")";
	}
	
	public int getId() {
		return this.id;
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
}
