package components;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import game.Mode;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;

import helpers.DefaultCoordinates4;
import helpers.DefaultCoordinates5;
import helpers.Direction;
import helpers.Orientation;
import helpers.exceptions.OutOfGridException;


public class Grid {
	
	/**
	 * Length of the sizes of the grid (a square)
	 * 
	 */
	private final static int size = 24;
	
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
	 * All the possible moves. This attribute is only used in the search algorithms.
	 */
	private Map<Line, Point> possibleMoves;
	
	/**
	 * Visual of the game on console
	 */
	private String[][] visual;

	public Grid() {
		this.grid = new HashMap<>();
        this.playablePoints = new HashMap<>();
        this.possibleMoves = new HashMap<>();
        this.lines = new HashSet<Line>();
        this.visual = new String [size][size];
	}
	
	/**
	 * Constructor to make a defensive copy of a grid
	 * 
	 * @param grid
	 */
	public Grid(Grid originalGrid) {
		this.grid = new HashMap<>();
	    for (Map.Entry<Integer, Point> entry : originalGrid.getGrid().entrySet()) {
	    	if (entry.getValue().isPlayed()) {
	    		PlayedPoint pointCopy = (PlayedPoint) entry.getValue();
		        this.grid.put(entry.getKey(), new PlayedPoint(pointCopy));
	    	}
	    	else this.grid.put(entry.getKey(), new Point(entry.getValue()));
	    }

	    this.playablePoints = new HashMap<>();
	    for (Map.Entry<Point, Set<Line>> entry : originalGrid.getPlayablePoints().entrySet()) {
	        Set<Line> lines = new HashSet<>();
	        for (Line line : entry.getValue()) {
	            lines.add(new Line(line)); // Assuming Line has a copy constructor
	        }
	        this.playablePoints.put(new Point(entry.getKey()), lines);
	    }

	    this.possibleMoves = new HashMap<>();
	    for (Map.Entry<Line, Point> entry : originalGrid.getPossibleMoves().entrySet()) {
	        this.possibleMoves.put(new Line(entry.getKey()), new Point(entry.getValue()));
	    }

	    this.lines = new HashSet<>();
	    for (Line line : originalGrid.getLines()) this.lines.add(new Line(line));
	    

	    this.visual = new String[originalGrid.getVisual().length][originalGrid.getVisual()[0].length];
	    for (int y = 0; y < originalGrid.getVisual().length; y++) {
	        for (int x = 0; x < originalGrid.getVisual()[0].length; x++) {
	            this.visual[y][x] = originalGrid.getVisual()[y][x];
	        }
	    }
	}
	
	public void initGrid() {		
		if (Mode.getNumber() == 5) initGrid5DT();
		else if (Mode.getNumber() == 4) initGrid4DT();
	}
	
	/**
	 * This method initializes the visual of the grid for mods with lines of 5 points
	 */
	public void initGrid5DT() {		
		for (int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				if (DefaultCoordinates5.getValues().contains(Objects.hash(y, x))) {
					grid.put(Objects.hash(y, x), new PlayedPoint(y, x));
					visual[y][x] = "X";
				}
				else {
					grid.put(Objects.hash(y,x), new Point(y, x));
					visual[y][x] = "*";
				}
			}
		}
	}
	
	/**
	 * This method initializes the visual of the grid for mods with lines of 4 points
	 */
	public void initGrid4DT() {		
		for (int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				if (DefaultCoordinates4.getValues().contains(Objects.hash(y, x))) {
					grid.put(Objects.hash(y, x), new PlayedPoint(y, x));
					visual[y][x] = "X";
				}
				else {
					grid.put(Objects.hash(y,x), new Point(y, x));
					visual[y][x] = "*";
				}
			}
		}
	}
	
	public void drawGrid() {
		System.out.println(this.toString());
//		for (int y = size-1; y >= 0 ; y--) {
//            for (int x = 0; x < size; x++) {
//                System.out.print(visual[y][x]+ " ");
//            }
//            System.out.print("\n");
//        }
//        System.out.println("\n");
	}
	
	public void updateVisualGrid() {
		for (Point point: playablePoints.keySet()) {
			visual[point.getY()][point.getX()] = "?";
		}
		for (Point gridPoint: this.grid.values()) {
			if (!this.playablePoints.containsKey(gridPoint) & (!gridPoint.isPlayed())) {
				visual[gridPoint.getY()][gridPoint.getX()] = "*";
			}
		}
	}
	
	/**
	 * This method is used to update informations of the grid according to the point that was played.
	 * 
	 * @param playedPoint
	 */
	public void updateGrid(PlayedPoint playedPoint, Line playedLine){
		this.updatePointStatus(playedPoint, playedLine);
		this.updateLines(playedPoint, playedLine);
	}

	/**
	 * Find all playable points
	 * 
	 * The search is limited to the sub grid defined by minPlayablePoint and maxPlayablePoint
	 * 
	 * It clears playable points before searching
	 */ 
	public void updatePlayablePoints() {
		this.playablePoints.clear();
		for (Map.Entry<Integer, Point> point: grid.entrySet()) {
			if (!(point.getValue().isPlayed())){
				Set<Line> possiblePointsForLine = (HashSet<Line>) this.findLinesAround(point.getValue());
				if (!(possiblePointsForLine.isEmpty())){
					playablePoints.put(point.getValue(), possiblePointsForLine);
				}
			}	
		}
		this.updatePossibleMoves();
//		this.updateVisualGrid();
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
		Set<Line> linesAround = new HashSet<>();
		for (Direction direction: Direction.allDirections()) {
			linesAround.addAll(this.findLinesInDirection(point, direction));
		}
		return linesAround;
	}

	/**
	 * This method search for possible line to form with one point. It searches in on specific direction {@link helpers.Direction}
	 * 
	 * @param point
	 * @param direction
	 * @return
	 */
	public Set<Line>findLinesInDirection(Point point, Direction direction) {
		Set<Line> lines = new HashSet<>();
		if (Mode.getType().toString().equals("T")) {
			Line jointLine = this.findJointLineInDirection(point, direction);
			if (!(jointLine == null)) lines.add(jointLine);
		}
		
		lines.addAll(findNormalLinesInDirection(point, direction));
		return lines;
	}
	
	/**
	 * This method searches for possible lines that are not joint
	 * 
	 * @param point
	 * @param direction
	 * @param mode
	 * @return
	 */
	public Set<Line> findNormalLinesInDirection(Point point, Direction direction){
		Set<Line> lines = new HashSet<>();
		List<Point> possiblePoints = new ArrayList<>();
		for (Point neighbour: this.getNeighboursInDirection(point, direction)) {
			if (
					grid.containsKey(neighbour.hashCode())
					&&
					(neighbour.isPlayed()) 
					&& 
					!(((PlayedPoint) neighbour).getInvolvedDirections().contains(direction))
				){
				possiblePoints.add(neighbour);
				if (possiblePoints.size() == Mode.getNumber() - 1) {
					possiblePoints.add(point);
					lines.add(new Line(new HashSet<>(possiblePoints), direction));
					possiblePoints.remove(possiblePoints.size() - 1); // remove the unplayed point
					possiblePoints.remove(0); // remove first point so we can search new line from new neighbour
				}
			}
			else possiblePoints.clear();
		}
		return lines;
	}
	
	/**
	 * This method searches if there is the possibility to form a joint line in a direction
	 * 
	 * For each orientation of the direction, it checks if the very close neighbour at a distance of 1
	 * is the end of a line that is in the same direction. 
	 * 		If yes, look it looks opposite orientation for a potential joint line
	 *		If no, we repeat the process for the opposite orientation
	 * 
	 * Considering these conditions:
	 * If we found a possible joint lien in one side; then it is not possible to find a possible joint line in the other side
	 * and the method is done
	 * 
	 * 
	 * @param point
	 * @param direction
	 * @return Line
	 */
	public Line findJointLineInDirection(Point point, Direction direction){
		Set<Point> possiblePoints = new HashSet<>();
		for (Orientation orientation: direction.getOrientations()){
			int neighbourHash = Objects.hash(point.getX() + orientation.getX(), point.getY() + orientation.getY());
			if (grid.containsKey(neighbourHash)) {
				if (this.getPoint(point.getX() + orientation.getX(), point.getY() + orientation.getY()).isPlayed()) {
					if (
							((PlayedPoint) this.grid.get(neighbourHash)).isEndOfLine() 
							&& 
							((PlayedPoint) this.grid.get(neighbourHash)).getInvolvedDirections().contains(direction)
						){
						possiblePoints.add(this.grid.get(neighbourHash));
						Orientation oppositeOrientation = direction.getOppositeOrientation(orientation);
						List<Integer> moveX = oppositeOrientation.moveX();
						List<Integer> moveY = oppositeOrientation.moveY();
						for (int i = 0; i <= moveX.size() - 1; i++) {
							int oppositeNeighbourHash = Objects.hash(point.getX() + moveX.get(i), point.getY() + moveY.get(i));
							if (grid.containsKey(oppositeNeighbourHash)) {
								if (
										this.grid.get(oppositeNeighbourHash).isPlayed()
										&&
										!((PlayedPoint) this.grid.get(oppositeNeighbourHash)).getInvolvedDirections().contains(direction)
									) {
									possiblePoints.add(this.grid.get(oppositeNeighbourHash));
									if (possiblePoints.size() == Mode.getNumber() - 1) {
										possiblePoints.add(point);
										return new Line(possiblePoints, direction);
									}
								}
							}	
						}
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * This method return the list of the neighbour at a distance n-1 (where n is the number of the mod 5D, 4D, etc)
	 * from each orientation of the direction. It returns the points in the order from left to right and bottom to top for vertical direction
	 * 
	 * EXAMPLE: 
	 * 	if we are searching in the horizontal direction, for (12, 15)
	 * 	it will return the list [(12, 11), (12, 12), (12, 13), (12, 14), (12, 16), (12, 17), (12, 18), (12, 19)]
	 * 	
	 *
	 * @param point
	 * @param direction
	 * @return the neighbours list
	 */
	public List<Point> getNeighboursInDirection(Point point, Direction direction){
		List<Point> neighboursList = new ArrayList<>();
		List<Integer> moveX = direction.moveX();
		List<Integer> moveY = direction.moveY();
		for (int i = 0; i < moveX.size(); i++) {
			int hash = Objects.hash(point.getX() + moveX.get(i), point.getY() + moveY.get(i));
			if (this.grid.containsKey(hash)) {
				neighboursList.add(this.grid.get(hash));
			}
		}
		return neighboursList;
	}
	
	/**
	 * Updating grid: update the type of the points of the line from Point to PlayedPoint in grid
	 * 
	 * @param playedPoint
	 */
	public void updatePointStatus(PlayedPoint playedPoint, Line playedLine) {
		this.grid.put(playedPoint.hashCode(), playedPoint);
	}
	
	/**
	 * This method is used to update the display in console
	 * 
	 * 1) Updating lines: add the line chosen and update the involvedDirections for each point of the played line
	 * 2) Updating visual: update the visual with the line and the id of the played point
	 * @param playedPoint
	 */
	public void updateLines(PlayedPoint playedPoint, Line playedLine) {
		playedLine.updatePlayedPoint(playedPoint);
		for (Point linePoint: playedLine.getPoints()) {
	        PlayedPoint p = (PlayedPoint) linePoint;
			p.addInvolvedDirection(playedLine.getDirection());
		}
		
		for (Point endsOfLine: playedLine.getEndsOfLine()) {
			((PlayedPoint) endsOfLine).setEndOfLine(true);
		}
		
		this.lines.add(playedLine);
		for (Point point: playedLine.getPoints()) {
			if (playedLine.getDirection() == Direction.HORIZONTAL && !(visual[point.getY()][point.getX()].matches("\\d+"))) visual[point.getY()][point.getX()] = "-";
			if (playedLine.getDirection() == Direction.VERTICAL && !(visual[point.getY()][point.getX()].matches("\\d+"))) visual[point.getY()][point.getX()] = "|";
			if (playedLine.getDirection() == Direction.DIAGONAL1 && !(visual[point.getY()][point.getX()].matches("\\d+"))) visual[point.getY()][point.getX()] = "/";
			if (playedLine.getDirection() == Direction.DIAGONAL2 && !(visual[point.getY()][point.getX()].matches("\\d+"))) visual[point.getY()][point.getX()] = "\\";
		}
		visual[playedPoint.getY()][playedPoint.getX()] =  "" + playedPoint.getId();
	}
	
	private void updatePossibleMoves() {
		this.possibleMoves.clear();
		for (Point playablePoint: this.playablePoints.keySet()) {
			for (Line possibleLine: this.playablePoints.get(playablePoint)) {
				possibleMoves.put(possibleLine, playablePoint);
			}
		}
	}
	
	@Override
	public String toString() {
		String toString = "";
		for (int y = size-1; y >= 0 ; y--) {
            for (int x = 0; x < size; x++) {
            	toString += visual[y][x]+ " ";
            }
            toString += "\n";
        }
		toString += "\n";
		return toString;
	}
	
	public boolean isPlayable(Point point) {
		return this.playablePoints.containsKey(point);
	}
	
	public void addLine(Line newLine) {
		this.lines.add(newLine);
	}
	
	public static int getSize(){
		return size;
	}
	
	public Map<Integer, Point> getGrid(){
		return this.grid;
	}
	
	public Set<Line> getLines(){
		return this.lines;
	}
	
	public Map<Point, Set<Line>> getPlayablePoints() {
		return this.playablePoints;
	}
	
	public Map<Line, Point> getPossibleMoves(){
		return this.possibleMoves;
	}
	
	public String[][] getVisual(){
		return this.visual;
	}
	
	public boolean contains(int x, int y) {
		return this.grid.containsKey(Objects.hash(x, y));
	}
	
	public Point getPoint(int x, int y) {
		if (x < 0 || y < 0) throw new OutOfGridException("Coordinates cannot be negative.");
		if (x >= size || y >= size) throw new OutOfGridException("The point is outside the grid.");
		return this.grid.get(Objects.hash(x, y));
	}
}