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
import java.lang.reflect.Array;


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
	private String [][] visual;

	public Grid() {
		this.size = 24;
		this.grid = new HashMap<>();
        this.playablePoints = new HashMap<>();
        this.lines = new HashSet<Line>();
        this.visual = new String [size][size];
	}
	
	public void initGrid() {
		for (int x = 0; x < size; x++) {
			for(int y = 0; y < size; y++) {
				if (DefaultCoordinates.getValues().contains(Objects.hash(x, y))) {
					grid.put(Objects.hash(x, y), new PlayedPoint(x, y));
					visual[x][y] = "X";
				}
				else {
					grid.put(Objects.hash(x,y), new Point(x, y));
					visual[x][y] = "*";
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
	
	public void updateVisualGrid (int x, int y, String c) {
		visual[x][y] = c;
	}
	
	public void updateVisualGrid() {
		Set<Point> keys = playablePoints.keySet();
		for (Point point: keys) {
			updateVisualGrid(point.getX(),point.getY(),"?");
		}
	}
	
	/**
	 * This method is used to update informations of the grid according to the point that was played.
	 * 
	 * @param playedPoint
	 */
	public void updateGrid(PlayedPoint playedPoint){
		this.updatePointStatus(playedPoint);
		this.updateLines(playedPoint);
		this.updatePlayablePoints(playedPoint);
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
			for (int i = 0; i< 4; i++) {
				int hash = Objects.hash(point.getX() + moveX.get(i), point.getY() + moveY.get(i));
				if (grid.containsKey(hash)) {
					if ((grid.get(hash).isPlayed()) && !(((PlayedPoint) grid.get(hash)).getInvolvedDirections().contains(direction))) {
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
	 * Updating grid: update the type of the played point from Point to PlayedPoint in grid
	 * 
	 * @param playedPoint
	 */
	public void updatePointStatus(PlayedPoint playedPoint) {
		ArrayList<Line> playableLines = new ArrayList<>(this.playablePoints.get(playedPoint));
		playedPoint.addInvolvedDirection(playableLines.get(0).getDirection());
		this.grid.put(playedPoint.hashCode(), playedPoint);
	}
	
	/**
	 * Updating lines: add the line chosen
	 * 
	 * @param playedPoint
	 */
	public void updateLines(PlayedPoint playedPoint) {
		ArrayList<Line> playableLines = new ArrayList<>(this.playablePoints.get(playedPoint));
		this.lines.add(playableLines.get(0));
		System.out.println("Forming line: " + playableLines.get(0));
		for (Point point: playableLines.get(0).getPoints()) {
			if (playableLines.get(0).getDirection() == Direction.HORIZONTAL) visual[point.getX()][point.getY()] = "|";
			if (playableLines.get(0).getDirection() == Direction.VERTICAL) visual[point.getX()][point.getY()] = "-";
			if (playableLines.get(0).getDirection() == Direction.DIAGONAL1) visual[point.getX()][point.getY()] = "\\";
			if (playableLines.get(0).getDirection() == Direction.DIAGONAL2) visual[point.getX()][point.getY()] = "/";
		}
		System.out.println("Id du point joué: " + playedPoint.getId());
		visual[playedPoint.getX()][playedPoint.getY()] =  "" + playedPoint.getId();
	}
	
	/**
	 * Updating playablePoint: remove the played point from playable point
	 * 
	 * @param playedPoint
	 */
	public void updatePlayablePoints(PlayedPoint playedPoint) {
		this.playablePoints.remove(playedPoint);
	}
	
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
		grid.updateVisualGrid(2, 3, "P");
		grid.drawGrid();
		grid.updatePlayablePoints();
//		for(Entry<Point, Set<Line>> entry: grid.getPlayablePoints().entrySet()) {
//			System.out.println("\nThe playable point: " + entry.getKey());
//			System.out.print("The lines: " + entry.getValue());
//		}
	}
}