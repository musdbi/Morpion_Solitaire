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
	 * 
	 */
	private int size;
	
	/**
	 * Memory of the grid. As the point are identified in memory with their hash code,
	 * we use it to navigate through the grid and especially to find playable points
	 * 
	 * Key (Integer): the hash code
	 * Value (Point): the point with the corresponding hashcode
	 */
	private  Map<Integer, Point> grid;
	
	/**
	 * All lines of the grid
	 */
	private Set<Line> lines;

	/**
	 * List of the points that the player can play in the next move
	 */
	private  Map<Point, Set<Line>> playablePoints;
	
	/**
	 * The bottom-left and top-right corners of the sub grid in which one we look for playable point
	 */
	private Point minPlayablePoint, maxPlayablePoint;

	public Grid() {
		this.size = 24;
		Point minPlayablePoint = new Point(8, 8);
		Point maxPlayablePoint = new Point(18, 18);
		this.grid = new HashMap<>();
        this.playablePoints = new HashMap<>();
        this.lines = new HashSet();
        this.minPlayablePoint = new Point(0,0);
        this.minPlayablePoint = new Point(11, 11);
	}
	
	public void initGrid() {
		for (int x = 0; x < size; x++) {
			for(int y = 0; y < size; y++) {
				if (DefaultCoordinates.getValues().contains(Objects.hash(x, y))) {
					grid.put(Objects.hash(x, y), new PlayedPoint(x, y));
				}
				else {
					grid.put(Objects.hash(x,y), new Point(x, y));
				}
			}
		}
	}
	
	public void updateGrid(Point playedPoint){
		this.grid.put(playedPoint.hashCode(), playedPoint);
		if (playedPoint.getX() <= minPlayablePoint.getX() || playedPoint.getY() <= playedPoint.getX()) {
			minPlayablePoint.move(playedPoint.getX() -1, playedPoint.getY() - 1);
		}
		if (playedPoint.getX() >= minPlayablePoint.getX() || playedPoint.getY() >= playedPoint.getX()) {
			minPlayablePoint.move(playedPoint.getX() + 1, playedPoint.getY() + 1);
		}
//		this.lines.add();
//		ajouter la ligne à jouer
	}

	/**
	 * Find all playable points
	 * 
	 * The search is limited to the sub grid defined by minPlayablePoint and maxPlayablePoint
	 */
	public void updatePlayablePoints() {
		for (Map.Entry<Integer, Point> point: grid.entrySet()) {
			if (!(point.getValue().isPlayed())){
				HashSet<Line> possibleLinesAround = (HashSet<Line>) this.findLinesAround(point.getValue());
				if (!(possibleLinesAround.isEmpty())){
					playablePoints.put(point.getValue(), possibleLinesAround);
				}
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
				if (!(grid.get(hash).isPlayed()) || ((PlayedPoint) grid.get(hash)).getInvolvedDirection().contains(direction)) {
					break;
				}else if(points.size() == 4){
					points.add(point);
					lines.add(new Line(points, direction));
					points.clear();
				}else {
					points.add(grid.get(hash));
				}
			}
		}
		return lines;
	}
	
	/** 
	 * @param point
	 * @return a list of the points that are at a distance of n-1 (where n is the number of points in a line) unities from the given point
	 */
//	public Map<Integer, Point> getSubGrid(Point minPlayablePoint, Point maxPlayablePoint){
//		HashMap<Integer, Point> subGrid = new HashMap<>();
//		
//		return new HashSet<>();
//	}
	
	/**
	 * This method check for the playability of a point by looking the possibilities around it
	 * 
	 * @param point
	 * @return true/false depending on the presence of the point in the playable points list
	 */
	public boolean checkPlayability(Point point) {
		return this.playablePoints.containsKey(point);
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
	
	public Set<Line> getLines(){
		return this.lines;
	}
	
	public Map<Point, Set<Line>> getPlayablePoints() {
		return this.playablePoints;
	}
	
	public Map<Integer, Point> getGrid(){
		return this.grid;
	}
	
	public static void main(String[] args) {

//		HashSet<Point> points = new HashSet<>();
//		Point p1 = new Point(1,1);
//		Point p2 = new Point(1,1);
//		Point p3 = new Point(1,3);
//		Point p4 = new Point(1,4);
//		Point p5 = new Point(1,5);
//		points.add(p1);
//		points.add(p2);
//		points.add(p3);
//		points.add(p4);
//		points.add(p5);
//		
//		Line line1 = new Line(points, Direction.VERTICAL);
//		Line line2 = new Line(points, Direction.VERTICAL);
//
//		System.out.println(line1.equals(line2));
//
//		System.out.println(line1.hashCode());
//		System.out.println(line2.hashCode());
		
		
//		ArrayList<Point> points = new ArrayList<>();
//        Point p1 = new Point(0, 0);
//        Point p2 = new PlayedPoint(1, 1);
//        points.add(p1);
//        points.add(p2);
//		Grid grid = new Grid();
//		grid.getMemory().put(1, p1);
//		grid.getMemory().put(2, p2);
		
		
//		Point oldPoint = new Point(0, 0);
//    	Point p2 = new Point(0,0);
//    	Point p3 = new Point(0,1);
//    	HashSet<Point> set1 = new HashSet<>();
//    	ArrayList<Point> l1 = new ArrayList<>();
//    	set1.add(oldPoint);
//    	set1.add(p3);
//    	set1.add(p2);
//    	l1.add(oldPoint);
//    	l1.add(p3);
//    	l1.add(p2);
//    	
//    	HashSet<Point> set2 = new HashSet<>();
//    	ArrayList<Point> l2 = new ArrayList<>();
//    	set2.add(p3);
//    	set2.add(p2);
//    	set2.add(oldPoint);
//    	l2.add(p3);
//    	l2.add(p2);
//    	l2.add(oldPoint);
//    	
//    	System.out.println(Objects.hash(l1));
//    	System.out.println(Objects.hash(l2));
//    	
//    	System.out.println(Objects.hash(set1.toArray()));
//    	System.out.println(Objects.hash(set2.toArray()));
//    	
//    	System.out.println(set1.equals(set1));
	
		
//    	System.out.println(Objects.hash(Direction.VERTICAL));
//    	System.out.println(Objects.hash(Direction.VERTICAL));
//    	
//    	System.out.println(Objects.hash(Orientation.N));
//    	System.out.println(Objects.hash(Orientation.N));

		
//		Grid grid = new Grid();
//		grid.getMemory().put(oldPoint.hashCode(), oldPoint);
//		
//		System.out.println(grid.getMemory());
//		System.out.println(grid.getMemory().get(oldPoint.hashCode()) instanceof Point);
//		
//		Point newPoint = new PlayedPoint(grid.getMemory().get(oldPoint.hashCode()));
//		grid.getMemory().put(newPoint.hashCode(), newPoint);
//		
//		System.out.println(grid.getMemory());
//		System.out.println(grid.getMemory().get(newPoint.hashCode()) instanceof PlayedPoint);
		
		
//		Point p1 = new Point(0, 0);
//		Grid grid = new Grid();
//		grid.getGrid().put(p1.hashCode(), p1);
//		System.out.println(grid.getGrid());
//		
//		Point p2 = new PlayedPoint(p1);
//		grid.getGrid().put(p2.hashCode(), p2);
//		System.out.println(grid.getGrid());
		
		
		
//		Grid grid = new Grid();
//		grid.initGrid();
//		grid.updatePlayablePoints();
//		for (Point point: grid.getGrid().values()) {
//			System.out.println(point);
//		}
//		for (Point point: grid.getPlayablePoints().keySet()) {
//			System.out.println(point);
//		}
		Orientation N = Orientation.N;
		ArrayList<Integer> list =  (ArrayList<Integer>) N.moveX();
		System.out.println(list);
	}
}