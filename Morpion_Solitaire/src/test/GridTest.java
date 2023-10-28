package test;

import components.Grid;
import components.Line;
import components.PlayedPoint;
import components.Point;
import helpers.Direction;

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
	private Point p6 = new Point(12, 10);
	private Point p7 = new Point(13, 10);
	private Point p8 = new Point(14, 10);
	private Point p9 = new Point(15, 10);
	private Point p10 = new Point(16, 10);
	
	
	
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

		Line line = new Line(points, Direction.HORIZONTAL);
		lines.add(line);
		
//		assertEquals(true, grid.getGrid().get(Objects.hash()).isPlayed());
//		assertEquals(false, grid.getGrid().get(Objects.hash(11,11)).isPlayed());
		
		assertEquals(lines, grid.findLinesInDirection(new Point(12, 10), Direction.VERTICAL));
	}
	
	@Test
	public void updatePlayablePoints() {
	}
	

}
