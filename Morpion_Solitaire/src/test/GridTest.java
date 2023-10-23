package test;

import components.Grid;
import components.Line;
import components.PlayedPoint;
import components.Point;
import constants.Direction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class GridTest {
	Grid grid = new Grid();

	private Point p1 = new Point(1,1);
	private Point p2 = new Point(1,1);
	private Point p1bis = new PlayedPoint(1, 1);
	private Point p3 = new Point(1,3);
	private Point p4 = new Point(1,4);
	private Point p5 = new Point(1,5);
	
	// Line test
	private Point p6 = new Point(10, 12);
	private Point p7 = new Point(9, 13);
	private Point p8 = new Point(11, 11);
	private Point p9 = new Point(12, 10);
	private Point p10 = new Point(13, 9);
	
	
	
	@Test
	public void testfindLinesInDirections() {
		grid.initGrid();
		Set<Point> points = new HashSet<>();
		Set<Line> lines = new HashSet<>();
		points.add(p6);
		points.add(p7);
		points.add(p8);
		points.add(p9);
		points.add(p10);

		Line line = new Line(points, Direction.DIAGONAL2);
		lines.add(line);
		
		assertEquals(true, grid.getGrid().get(Objects.hash(12,10)).isPlayed());
		assertEquals(false, grid.getGrid().get(Objects.hash(11,11)).isPlayed());
		
		assertEquals(lines, grid.findLinesInDirection(new Point(11, 11), Direction.DIAGONAL2));
	}
	
	@Test
	public void updatePlayablePoints() {
	}
	

}
