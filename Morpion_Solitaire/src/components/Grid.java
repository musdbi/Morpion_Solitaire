package components;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Grid {
	
	/**
	 * Length of the sizes of the grid (a square)
	 */
	private int size;
	
	/**
	 * All the points of the grid
	 */
	private List<Point> gridPoints;
	
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
	 * This method check for the playability of a point by looking the possibilities around it
	 * 
	 * @param point
	 * @return 
	 */
	public boolean checkPlayability(Point point) {
		return false;
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
