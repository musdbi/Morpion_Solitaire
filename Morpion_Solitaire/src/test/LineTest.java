package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import components.PlayedPoint;
import components.Point;
import helpers.Direction;
import components.Line;

public class LineTest {
	private Point p1 = new Point(1,1);
	private Point p2 = new Point(1,2);
	private Point p3 = new Point(1,3);
	private Point p4 = new Point(1,4);
	private Point p5 = new Point(1,5);
	private Point p6 = new Point(1,6);
	private Point p7 = new PlayedPoint(1, 7);
	private PlayedPoint p7 = new PlayedPoint(1, 7);

	
	public LineTest() {
	}
	
	@Test
	public void testEquals() {
		Set<Point> points = new HashSet<>();
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		points.add(p5);
		
		Line line1 = new Line(points, Direction.VERTICAL);
		Line line2 = new Line(points, Direction.VERTICAL);
		points.remove(p1);
		points.add(p6);
		Line line3 = new Line(points, Direction.VERTICAL);
		assertEquals(true, line1.equals(line2));
		assertEquals(false, line2.equals(line3));
	}
	
	@Test
	public void testHashCode() {
		Set<Point> points = new HashSet<>();
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		points.add(p5);
		
		Line line1 = new Line(points, Direction.VERTICAL);
		Line line2 = new Line(points, Direction.VERTICAL);
		points.remove(p1);
		points.add(p6);
		Line line3 = new Line(points, Direction.VERTICAL);
		
		assertEquals(true, line1.hashCode() == line2.hashCode());
		assertEquals(false, line2.hashCode() == line3.hashCode());
	}
	@Test
	public void testDirection() {
		Set<Point> points = new HashSet<>();
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		points.add(p5);
		
		System.out.println(points);
		Line line1 = new Line(points, Direction.VERTICAL);
		assertEquals(Direction.VERTICAL, line1.getDirection());
	}
	
	@Test
	public void testDirection() {
		Set<Point> points = new HashSet<>();
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		points.add(p5);
		
		System.out.println(points);
		Line line1 = new Line(points, Direction.VERTICAL);
		assertEquals(Direction.VERTICAL, line1.getDirection());
	}
}
