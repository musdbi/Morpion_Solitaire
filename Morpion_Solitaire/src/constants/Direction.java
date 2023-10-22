package constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import components.Orientation;
import components.Point;

public enum Direction {
	
	HORIZONTAL(Orientation.W, Orientation.E),
	VERTICAL(Orientation.N, Orientation.S),
	DIAGONAL(Orientation.SW, Orientation.NE);
	
	private Orientation orientation1;
	private Orientation orientation2;
	
	Direction(Orientation w, Orientation e) {
	}
	
	public static List<Direction> allDirections() {
		ArrayList<Direction> directions = new ArrayList<>();
		directions.add(Direction.HORIZONTAL);
		directions.add(Direction.VERTICAL);
		directions.add(Direction.DIAGONAL);
		return directions;
	}
	
	
	public List<Orientation> orientations() {
		ArrayList<Orientation> orientations = new ArrayList<>();
		orientations.add(orientation1);
		orientations.add(orientation2);
		return orientations;
	}
}
