package components;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import constants.DefaultCoordinates;
import constants.Direction;

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
	
	public PlayedPoint(int x, int y) {
		super(x, y);
		if (!(DefaultCoordinates.getValues().contains(Objects.hash(x, y)))) {
			playedPointsCount += 1;
			this.id = playedPointsCount;
		}
		this.involvedInDirections = new HashSet<>();
	}
	
	public PlayedPoint(Point p) {
		 super(p.getX(), p.getY());
		playedPointsCount += 1;
		this.id = playedPointsCount;
	}
	
	@Override
	public String toString() {
		return "Played point: (" + this.x + "," + this.y + ")";
	}
	
	public static void resetCount() {
		playedPointsCount = 0;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getCount() {
		return playedPointsCount;
	}
	
	public Set<Direction> getInvolvedDirections(){
		return this.involvedInDirections;
	}
	
	public void addInvolvedDirection(Direction direction) {
		this.involvedInDirections.add(direction);
	}
}
