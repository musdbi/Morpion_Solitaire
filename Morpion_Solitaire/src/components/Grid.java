package components;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

import constants.DefaultCoordinates;
import constants.Direction;
import constants.Orientation;

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
	 * Key (Point): the playable point
	 * Value (Set<Line>): all the line that could be formed with this point
	 */
	private  Map<Point, Set<Line>> playablePoints;
	/**
	 * Visual of the game on console
	 */
	private char [][] visual;

	public Grid() {
		this.size = 24;
		this.grid = new HashMap<>();
        this.playablePoints = new HashMap<>();
        this.lines = new HashSet();
        this.visual = new char [size][size];
	}
	
	public void initGrid() {
		for (int x = 0; x < size; x++) {
			for(int y = 0; y < size; y++) {
				if (DefaultCoordinates.getValues().contains(Objects.hash(x, y))) {
					grid.put(Objects.hash(x, y), new PlayedPoint(x, y));
					visual[x][y] = 'X';
				}
				
				else {
					grid.put(Objects.hash(x,y), new Point(x, y));
					visual[x][y] = '*';
					
				}
			}
		}
		this.drawGrid();
	}
	
	public void drawGrid() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				System.out.print(visual[x][y]+ " ");
			}
			System.out.print("\n");
		}
		System.out.println("\n-----------------------------------------------\n");
	}
	
	public void updateVisualGrid (int x, int y, char c) {
		visual[x][y] = c;
	}
	
	public void updateVisualGrid() {
		Set<Point> keys = playablePoints.keySet();
		for (Point point: keys) {
			updateVisualGrid(point.getX(),point.getY(),'?');
		}
	}
	
	/**
	 * This method is used to update informations of the grid according to the point that was played.
	 * 
	 * @param playedPoint
	 */
	public void updateGrid(Point playedPoint){
//		this.grid.put(playedPoint.hashCode(), playedPoint);
//		if (playedPoint.getX() <= minPlayablePoint.getX() || playedPoint.getY() <= playedPoint.getX()) {
//			minPlayablePoint.move(playedPoint.getX() -1, playedPoint.getY() - 1);
//		}
//		if (playedPoint.getX() >= minPlayablePoint.getX() || playedPoint.getY() >= playedPoint.getX()) {
//			minPlayablePoint.move(playedPoint.getX() + 1, playedPoint.getY() + 1);
//		}
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
		updateVisualGrid();
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

	/**
	 * This method search for possible line to form with one point. It searches in on specific direction {@link constants.Direction}
	 * 
	 * @param point
	 * @param direction
	 * @return
	 */
	public Set<Line>findLinesInDirection(Point point, Direction direction) {
		HashSet<Line> lines = new HashSet<>();
		HashSet<Point> points  = new HashSet<>();
		for (Orientation orientation: direction.orientations()) {
			List<Integer> moveX = orientation.moveX();
			List<Integer> moveY = orientation.moveY();
			// 4 is variable according to the mod
			for (int i = 0; i< 4; i++) {
//				if (point.equals(new Point(12, 10))) {
//					System.out.println(points);
//				}
				int hash = Objects.hash(point.getX() + moveX.get(i), point.getY() + moveY.get(i));
				if (grid.containsKey(hash)) {
					if ((grid.get(hash).isPlayed()) && !(((PlayedPoint) grid.get(hash)).getInvolvedDirection().contains(direction))) {
						points.add(grid.get(hash));
						if(points.size() == 4){
							points.add(point);
							lines.add(new Line(points, direction));
							points.clear();
						}
					}
					else {
						break;
					}
				}
			}
		}
		return lines;
	}
	
	/**
	 * This method check for the playability of a point by looking the possibilities around it
	 * 
	 * @param point
	 * @return true/false depending on the presence of the point in the playable points list
	 */
	public boolean checkPlayability(Point point) {
		return this.playablePoints.containsKey(point);
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
		Grid grid = new Grid();
		grid.initGrid();
		grid.updateVisualGrid(2, 3, 'P');
		grid.drawGrid();
//		if (grid.getGrid().get(Objects.hash(16, 10)).isPlayed()) {
//			System.out.println("16, 10 is a played point");
//		}
//		else {
//			System.out.println("no");
//		}
		grid.updatePlayablePoints();
		grid.drawGrid();
//		for(Entry<Point, Set<Line>> entry: grid.getPlayablePoints().entrySet()) {
//			System.out.println("\nThe playable point: " + entry.getKey());
//			System.out.print("The lines: " + entry.getValue());
//		}
	}
}