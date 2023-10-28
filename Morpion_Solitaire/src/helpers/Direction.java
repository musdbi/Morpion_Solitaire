package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import components.Point;

public enum Direction {
	
	HORIZONTAL(Orientation.W, Orientation.E),
	VERTICAL(Orientation.N, Orientation.S),
	DIAGONAL1(Orientation.SW, Orientation.NE),
	DIAGONAL2(Orientation.SE, Orientation.NW);
	
	private Orientation orientation1;
	private Orientation orientation2;
	
	Direction(Orientation orientation1, Orientation orientation2) {
		this.orientation1 = orientation1;
		this.orientation2 = orientation2;
	}
	
	public static List<Direction> allDirections() {
		ArrayList<Direction> directions = new ArrayList<>();
		directions.add(Direction.HORIZONTAL);
		directions.add(Direction.VERTICAL);
		directions.add(Direction.DIAGONAL1);
		directions.add(Direction.DIAGONAL2);
		return directions;
	}
	
	
	public List<Orientation> orientations() {
		ArrayList<Orientation> orientations = new ArrayList<>();
		orientations.add(orientation1);
		orientations.add(orientation2);
		return orientations;
	}
}
