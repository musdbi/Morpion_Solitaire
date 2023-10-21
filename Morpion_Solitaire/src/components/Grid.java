package components;

import java.awt.Point;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import constants.Direction;
import java.util.ArrayList;
import java.lang.Math;


public class Grid {
	
	/**
	 * Length of the sizes of the grid (a square)
	 */
	private int size;
	
	/**
	 * All the points of the grid
	 */
	private  List<Point> gridPoints;
	
	/**
	 * All lines of the grid
	 */
	private List<Line> lines;

	/**
	 * List of the points that the player can play in the next round
	 */
	private Map<List<Point>, Point> playablePoints;

	public Grid() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Find all playable points
	 * The search is limited to the sub grid defined by minPlayablePoint and MaxPlayable Point
	 */
	public void updatePlayablePoints() {		
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
	public Set<Set<Point>> findLinesAround(PlayedPoint point, List<Point> subGridAround) {
//		for (Point pointAround: subGridAround)
		return new ArrayList<Point>();
	}
	
	/** 
	 * @param point
	 * @return a list of the points that are at a distance of n-1 (where n is the number of points in a line) unities from the given point
	 */
	public List<Point> getSubGridAround(Point point){
		ArrayList<Point> subGridAround = new ArrayList<Point>();
		for (Point gridPoint: gridPoints) {
			if (PointUtils.distance(point, gridPoint) <= 4 && PointUtils.isNormalPoint(gridPoint)){subGridAround.add(gridPoint);}
		}
		return subGridAround;
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
	
	public List<Point> getGridPoints(){
		return this.gridPoints;
	}
	
	public List<Line> getGridLines(){
		return this.lines;
	}
	
	public Map<List<Point>, Point> getPlayablePoints() {
		return this.playablePoints;
	}
}
