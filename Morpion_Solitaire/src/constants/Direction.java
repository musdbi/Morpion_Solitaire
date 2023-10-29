package constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import components.Point;

public enum Direction {
	
	HORIZONTAL(Orientation.W, Orientation.E),
	VERTICAL(Orientation.S, Orientation.N),
	DIAGONAL1(Orientation.SW, Orientation.NE),
	DIAGONAL2(Orientation.SE, Orientation.NW);
	
	private Orientation orientation1;
	private Orientation orientation2;
	
	Direction(Orientation orientation1, Orientation orientation2) {
		this.orientation1 = orientation1;
		this.orientation2 = orientation2;
	}
	
	public List<Integer> moveX(){
		List<Integer> moveX = new ArrayList<>();
		for (int x = 4; x > 0; x --) {
			moveX.add(this.orientation1.getX() * x);
		}
		for (int x = 1; x <=4; x ++) {
			moveX.add(this.orientation2.getX() * x);
		}
		return moveX;
	}
	
	public List<Integer> moveY(){
		List<Integer> moveY = new ArrayList<>();
		for (int y = 4; y > 0; y --) {
			moveY.add(this.orientation1.getY() * y);
		}
		for (int y = 1; y <=4; y ++) {
			moveY.add(this.orientation2.getY() * y);
		}
		return moveY;
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
