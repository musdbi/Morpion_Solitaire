package components;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import helpers.DefaultCoordinates;
import helpers.Direction;


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
	}
	
	public void drawGrid() {
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				System.out.print(visual[x][y]+ " ");
			}
			System.out.print("\n");
		}
		System.out.println("\n");
	}
	
	public void updateVisualGrid() {
		for (Point point: playablePoints.keySet()) {
			visual[point.getX()][point.getY()] = "?";
		}
		for (Point gridPoint: this.grid.values()) {
			if (!this.playablePoints.containsKey(gridPoint) & (!gridPoint.isPlayed())) {
				visual[gridPoint.getX()][gridPoint.getY()] = "*";
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
		this.updateVisualGrid();
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
//	d
	/**
	 * This method search for possible line to form with one point. It searches in on specific direction {@link helpers.Direction}
	 * 
	 * @param point
	 * @param direction
	 * @return
	 */
	public Set<Line>findLinesInDirection(Point point, Direction direction) {
		Set<Line> lines = new HashSet<>();
		List<Point> possiblePoints = new ArrayList<>();
		
		for (Point neighbour: this.getNeighboursInDirection(point, direction)) {
			if (grid.containsKey(neighbour.hashCode())) {
				if ((neighbour.isPlayed()) && !(((PlayedPoint) neighbour).getInvolvedDirections().contains(direction))) {
					possiblePoints.add(neighbour);
					if (possiblePoints.size() == 4) {
						possiblePoints.add(point);
						lines.add(new Line(new HashSet<>(possiblePoints), direction));
						possiblePoints.remove(0);
					}
				}
				else {
					possiblePoints.clear();
				}
			}
		}
		return lines;
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
		for (int i = 0; i < 8; i++) {
			int hash = Objects.hash(point.getX() + moveX.get(i),point.getY() + moveY.get(i));
			if (this.grid.containsKey(hash)) {
				neighboursList.add(this.grid.get(hash));
			}
		}
		return neighboursList;
	}
	
	/**
	 * Updating grid: update the type of the played point from Point to PlayedPoint in grid
	 * 
	 * @param playedPoint
	 */
	public void updatePointStatus(PlayedPoint playedPoint, Line playedLine) {
		this.grid.put(playedPoint.hashCode(), playedPoint);
	}
	
	/**
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
		
		System.out.println("Ligne jouée: " + playedLine);
		for (Point point: playedLine.getPoints()) {
			if (playedLine.getDirection() == Direction.HORIZONTAL) visual[point.getX()][point.getY()] = "|";
			if (playedLine.getDirection() == Direction.VERTICAL) visual[point.getX()][point.getY()] = "-";
			if (playedLine.getDirection() == Direction.DIAGONAL1) visual[point.getX()][point.getY()] = "\\";
			if (playedLine.getDirection() == Direction.DIAGONAL2) visual[point.getX()][point.getY()] = "/";
		}
		System.out.println("Id du point joué: " + playedPoint.getId());
		visual[playedPoint.getX()][playedPoint.getY()] =  "" + playedPoint.getId();
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
		Set<Point> points = new HashSet<>();
		PlayedPoint p1 = new PlayedPoint(1,1);
		PlayedPoint p2 = new PlayedPoint(1,2);
		PlayedPoint p3 = new PlayedPoint(1,3);
		PlayedPoint p4 = new PlayedPoint(1,4);
		Point p5 = new Point(1,5);
		PlayedPoint pp5 = new PlayedPoint(1, 5);
		
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		points.add(p5);
		
		Line line1 = new Line(points, Direction.VERTICAL);
		System.out.println(line1);
//		line1.getPoints().remove(pp5);
//		line1.getPoints().add(pp5);
		line1.updatePlayedPoint(pp5);
		System.out.println(line1);

	}
}