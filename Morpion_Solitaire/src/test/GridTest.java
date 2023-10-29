package test;

import components.Grid;
import components.Line;
import components.PlayedPoint;
import components.Point;
import constants.Direction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	// HORIZONTAL
	private Point p6 = new Point(12, 10);
	private Point p7 = new Point(13, 10);
	private Point p8 = new Point(14, 10);
	private Point p9 = new Point(15, 10);
	private Point p10 = new Point(16, 10);
	private Point p11 = new Point(17, 10);
	private Point p12 = new Point(18, 10);
	private Point p13 = new Point(19, 10);
	private Point p14 = new Point(20, 10);
	private Point p15 = new Point(21, 10);
	
	// VERTICAL
	private Point pa = new Point(12, 11);
	private Point pb = new Point(12, 12);
	private Point pc = new Point(12, 13);
	private Point pd = new Point(12, 14);
	private Point pe = new Point(12, 15);
	private Point pf = new Point(12, 16);
	private Point pg = new Point(12, 17);
	private Point ph = new Point(12, 18);
	private Point pi = new Point(12, 19);
	
	
//	@Test
//	public void testfindLinesInDirections() {
//		grid.initGrid();
//		Set<Point> points = new HashSet<>();
//		Set<Line> lines = new HashSet<>();
//		points.add(p6);
//		points.add(p7);
//		points.add(p8);
//		points.add(p9);
//		points.add(p10);
//
//		Line line = new Line(points, Direction.HORIZONTAL);
//		lines.add(line);
//		
////		assertEquals(true, grid.getGrid().get(Objects.hash()).isPlayed());
////		assertEquals(false, grid.getGrid().get(Objects.hash(11,11)).isPlayed());
//		
//		assertEquals(lines, grid.findLinesInDirection(new Point(12, 10), Direction.VERTICAL));
//	}
	
	@Test
	public void testGetNeighboursInDirection() {
		grid.initGrid();
		List<Point> neighbours = new ArrayList<>();
		
		neighbours.add(p6);
		neighbours.add(p7);
		neighbours.add(p8);
		neighbours.add(p9);
		
		neighbours.add(p11);
		neighbours.add(p12);
		neighbours.add(p13);
		neighbours.add(p14);
		
		assertEquals(neighbours, grid.getNeighboursInDirection(p10, Direction.HORIZONTAL));
		
		neighbours.clear();
		neighbours.add(pa);
		neighbours.add(pb);
		neighbours.add(pc);
		neighbours.add(pd);
		
		neighbours.add(pf);
		neighbours.add(pg);
		neighbours.add(ph);
		neighbours.add(pi);
		
		assertEquals(neighbours, grid.getNeighboursInDirection(pe, Direction.VERTICAL));

	}
	

}
