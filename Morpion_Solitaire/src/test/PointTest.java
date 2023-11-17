package test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

import components.PlayedPoint;
import components.Point;
import helpers.Direction;

public class PointTest {
	private Point p1 = new Point(1,1);
	private Point p2 = new Point(1,1);
	private Point p1bis = new PlayedPoint(1, 1);
	
	private Point p3 = new Point(1,3);
	private Point p4 = new Point(1,4);
	private Point p5 = new Point(1,5);
	
	@Test
	public void testEquals() {
		assertEquals(true, p1.equals(p2));
		assertEquals(true, p1.equals(p1bis));

		assertEquals(false, p1.equals(p3));
		assertEquals(false, p1bis.equals(p3));
	}
	
	@Test
	public void testHashCode() {
		assertEquals(Objects.hash(p1.getX(), p1.getY()), p1.hashCode());
		assertEquals(true, p1.hashCode() == p2.hashCode());
		assertEquals(true, p1.hashCode() == p1bis.hashCode());

		assertEquals(false, p1.hashCode() == p3.hashCode());
		assertEquals(false, p1bis.hashCode() == p3.hashCode());
	}
	
	@Test
	public void testIsPlayed() {
		assertEquals(false, p1.isPlayed());
		Point playedPoint = new PlayedPoint(1, 1);
		assertEquals(true, playedPoint.isPlayed());
	}
	
	@Test
	public void testInvolvedDirection() {
		PlayedPoint playedPoint = new PlayedPoint(1, 1);
		playedPoint.addInvolvedDirection(Direction.VERTICAL);
		assertEquals(
				true, 
				playedPoint.getInvolvedDirections().contains(Direction.VERTICAL) && 
				playedPoint.getInvolvedDirections().size() == 1
				);
	}
}
