package components;

import java.util.Objects;
import java.util.Set;

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
	
	private Set<Direction> involvedInDirections;
	
	public PlayedPoint(int x, int y) {
		super(x, y);
		if (!(DefaultCoordinates.getValues().contains(Objects.hash(x, y))));
			playedPointsCount += 1;
			this.id = playedPointsCount;
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
	
	public Set<Direction> getInvolvedDirection(){
		return this.involvedInDirections;
	}
}
