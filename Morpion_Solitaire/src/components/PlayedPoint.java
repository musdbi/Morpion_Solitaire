package components;

import helpers.DefaultCoordinates4;
import helpers.DefaultCoordinates5;
import helpers.Direction;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import game.Mode;

public class PlayedPoint extends Point{
	
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
	
	public PlayedPoint(int x, int y, int id) {
		super(x, y);
		this.involvedInDirections = new HashSet<>();
		this.isEndOfLine = false;
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Played point: (" + this.getX() + "," + this.getY() + ")";
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
	
	public static void main(String[] args) {
		PlayedPoint p = new PlayedPoint(0, 0);
		p.addInvolvedDirection(Direction.DIAGONAL1);
		System.out.println(p.getInvolvedDirections());
	}
}
