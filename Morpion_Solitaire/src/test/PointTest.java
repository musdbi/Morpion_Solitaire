package test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Point;
import components.PlayedPoint;

public class PointTest {
	private Point p1 = new Point(1,1);
	private Point p2 = new Point(1,1);
	private PlayedPoint p1bis = new PlayedPoint(1, 1);
	
	private Point p3 = new Point(1,3);
	private Point p4 = new Point(1,4);
	private Point p5 = new Point(1,5);
	
	@Test
	public void testEquals() {
		assertEquals(true, p1.equals(p2));
		
	}
}
