package helpers;

import java.util.HashSet;
import java.util.Objects;

import helpers.exceptions.OutOfGridException;

public enum DefaultCoordinates4 {
	p1(10, 8),
	p2(11, 8),
	p3(12, 8),
	p4(12, 9),
	p6(12, 10),
	p7(13, 10),
	p8(14, 10),
	p9(14, 11),
	p10(14, 12),
	p12(13, 12),
	p13(12, 12),
	p14(12, 13),
	p15(12, 14),
	p16(11, 14),
	p17(10, 14),
	p18(10, 13),
	p19(10, 12),
	p20(9, 12),
	p21(8, 12),
	p22(8, 11),
	p23(8, 10),
	p24(9, 10),
	p26(9, 12),
	p27(10, 9),
	p28(10, 10);
	
	private int x;
	private int y;
	
	DefaultCoordinates4(int x, int y) throws OutOfGridException {
		if (x < 0 || y < 0){
			throw new OutOfGridException("Coordinates cannot be negative.");
		}
		if (x >= 24 || y >= 24){
			throw new OutOfGridException("The point is outside the grid.");
		}
		this.x = x;
		this.y = y;
	}
	
	private int getX() {
		return this.x;
	}
	
	private int getY() {
		return this.y;
	}
	
	public static HashSet<Integer> getValues() {
		HashSet<Integer> defaultPoints = new HashSet<>();
        for (DefaultCoordinates4 coord: DefaultCoordinates4.values()) {
            defaultPoints.add(Objects.hash(coord.getX(), coord.getY()));
        }
        return defaultPoints;
    }
}


