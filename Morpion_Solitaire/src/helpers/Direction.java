package helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

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
		
		List<Integer> reversedOrientation1 = this.orientation1.moveX();
		Collections.reverse(reversedOrientation1);
		moveX.addAll(reversedOrientation1);
		
		moveX.addAll(this.orientation2.moveX());
		return moveX;
	}
	
	public List<Integer> moveY(){
		List<Integer> moveY = new ArrayList<>();
		
		List<Integer> reversedOrientation1 = this.orientation1.moveY();
		Collections.reverse(reversedOrientation1);
		moveY.addAll(reversedOrientation1);
		
		moveY.addAll(this.orientation2.moveY());
		
		return moveY;
	}
	
	public Orientation getOppositeOrientation(Orientation currOrientation) {
		for (Orientation orientation: this.getOrientations()) {
			if (!orientation.equals(currOrientation)) {
				return orientation;
			}
		}
		return null;
	}
	
	public static List<Direction> allDirections() {
		ArrayList<Direction> directions = new ArrayList<>();
		directions.add(Direction.HORIZONTAL);
		directions.add(Direction.VERTICAL);
		directions.add(Direction.DIAGONAL1);
		directions.add(Direction.DIAGONAL2);
		return directions;
	}
	
	public List<Orientation> getOrientations() {
        List<Orientation> orientations = new ArrayList<>();
        orientations.add(orientation1);
        orientations.add(orientation2);
        return orientations;
    }
	
	public static void main(String[] args) {
		System.out.println(DIAGONAL2.moveX());
		System.out.println(DIAGONAL2.moveY());

	}
}
