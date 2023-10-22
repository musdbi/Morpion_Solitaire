package components;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

import constants.Direction;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;


public class Grid {
	
	/**
	 * Length of the sizes of the grid (a square)
	 */
	private int size;
	
	/**
	 * Artificial memory of the grid
	 * Key: the hash code
	 * Value: the point with the corresponding hashcode
	 */
	private  Map<Integer, Point> memory;
	
	private Set<Point> unplayeddPoints;
	
	/**
	 * All lines of the grid
	 */
	private List<Line> lines;

	/**
	 * List of the points that the player can play in the next move
	 */
	private  Map<Point, Set<Line>> playablePoints;

	public Grid() {
        memory = new HashMap<>();
        playablePoints = new HashMap<>();
	}
	
	/**
	 * Find all playable points
	 * The search is limited to the sub grid defined by minPlayablePoint and MaxPlayable Point
	 */
	public void updatePlayablePoints() {
		for (Point point: this.unplayedPoint) {
			HashSet<Line> possibleLinesAround = (HashSet<Line>) this.findLinesAround(point);
			if (!(possibleLinesAround.isEmpty()){
				playablePoints.add(point);
			}
		}
	}

	/**
	 * This method find the possible lines that can be create from a given Played Point
	 * 
	 * @param 
	 * 		point
	 * @return 
	 * 		a List of Point composed of n-1 played points (where n is the mode: 5T 4T 6T, etc) and one "normal" point,
	 * 		where the normal point would be a playable point for the next move.
	 * 		an empty List if there is no playable point for the next move from the current point
	 */
	public Set<Line> findLinesAround(Point point) {
		HashSet<Line> linesAround = new HashSet<>();
		for (Direction direction: Direction.allDirections()) {
			linesAround.addAll(this.findLinesInDirection(point, direction));
		}
		return linesAround;
	}
	
	public Set<Line>findLinesInDirection(Point point, Direction direction) {
		HashSet<Line> lines = new HashSet<>();
		HashSet<Point> points  = new HashSet<>();
		for (Orientation orientation: direction.orientations()) {
			List<Integer> moveX = orientation.moveX();
			List<Integer> moveY = orientation.moveY();
			// 4 is variable according to the mod
			for (int i = 0; i< 4; i++) { 
				int hash = Objects.hash(point.getX() + moveX.get(i), point.getY() + moveY.get(i));
				if (memory.get(hash) instanceof Point || ((PlayedPoint) memory.get(hash)).getDirection().contains(direction)) {
					break;
				}else if(points.size() == 4){
//					créer ligne et ajouter à lines
					points.clear();
				}else {
//					linePoints.add(memory.get(hash));
				}
			}
		}
		return lines;
	}
	
	/** 
	 * @param point
	 * @return a list of the points that are at a distance of n-1 (where n is the number of points in a line) unities from the given point
	 */
	public Set<Point> getSubGrid(Point point){
		return new HashSet<>();
	}
	
	/**
	 * This method check for the playability of a point by looking the possibilities around it
	 * 
	 * @param point
	 * @return true/false depending on the presence of the point in the playable points list
	 */
	public boolean checkPlayability(Point point) {
		return this.playablePoints.containsValue(point);
	}
	
	/**
	 * This method is used for different mod "5D" and "5T"
	 * 
	 * For example: for 5D, if you find a playable point just next to the point,
	 * then the point is not playable because the line must be disjoint with another
	 * 
	 * @param point
	 * @return
	 */
	public List<Point> findPlayebPointsAround(Point point){
		return new ArrayList<>();
	}
	
	public void addLine(Line newLine) {
		this.lines.add(newLine);
	}
	
	public int getSize(){
		return this.size;
	}
	
	public List<Line> getGridLines(){
		return this.lines;
	}
	
	public Map<Point, Set<Line>> getPlayablePoints() {
		return this.playablePoints;
	}
	
	public Map<Integer, Point> getMemory(){
		return this.memory;
	}
	
	public static void maing(String[] args) {
//		ArrayList<Point> points = new ArrayList<>();
//        Point p1 = new Point(0, 0);
//        Point p2 = new PlayedPoint(1, 1);
//        points.add(p1);
//        points.add(p2);
//		Grid grid = new Grid();
//		grid.getMemory().put(1, p1);
//		grid.getMemory().put(2, p2);
		
		Point p1 = new Point(0, 0);
		Point p2 = new PlayedPoint(1, 1);
		Grid grid = new Grid();
		grid.getMemory().put(p1.hashCode(), p1);
		System.out.println(grid.getMemory().get(p1.hashCode()));
	}
}
