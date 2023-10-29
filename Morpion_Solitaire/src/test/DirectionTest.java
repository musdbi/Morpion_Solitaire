package test;

import org.junit.jupiter.api.Test;

import components.Line;
import components.Point;
import helpers.Direction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DirectionTest {

	@Test
	public void testEquals() {
		List<Integer> testList = new ArrayList<>();
		for (int i = -4; i <=4; i++) {
			testList.add(i);
		}
		testList.remove(4);
		
		assertEquals(testList, Direction.HORIZONTAL.moveX());
		
		testList.clear();
		for (int i = 1; i <= 8; i++) {
			testList.add(i * 0);
		}
		
		assertEquals(testList, Direction.VERTICAL.moveX());
	}
}
