package constants;

import java.util.ArrayList;
import java.util.List;

import components.Orientation;

public enum Direction {
	
	HORIZONTAL(Orientation.W, Orientation.E),
	VERTICAL(Orientation.N, Orientation.S),
	DIAGONAL(Orientation.SW, Orientation.NE);
	
	private Orientation orientation1;
	private Orientation orientation2;
	
	Direction(Orientation w, Orientation e) {
	}
	
	public List<Orientation> orientations() {
		ArrayList<Orientation> orientations = new ArrayList<>();
		orientations.add(orientation1);
		orientations.add(orientation2);
		return orientations;
	}
}
