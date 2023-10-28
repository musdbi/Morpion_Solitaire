package helpers;

import java.util.HashSet;
import java.util.Objects;

public enum DefaultCoordinates {
	p1(10, 7),
	p2(11, 7),
	p3(12, 7),
	p4(13, 7),
	p5(13, 8),
	p6(13, 9),
	p7(13, 10),
	p8(14, 10),
	p9(15, 10),
	p10(16, 10),
	p12(16, 11),
	p13(16, 12),
	p14(16, 13),
	p15(15, 13),
	p16(14, 13),
	p17(13, 13),
	p18(13, 14),
	p19(13, 15),
	p20(13, 16),
	p21(12, 16),
	p22(11, 16),
	p23(10, 16),
	p24(10, 15),
	p25(10, 14),
	p26(10, 13),
	p27(9, 13),
	p28(8, 13),
	p29(7, 13),
	p30(7, 12),
	p31(7, 11),
	p32(7, 10),
	p33(8, 10),
	p34(9, 10),
	p35(10, 10),
	p36(10, 9),
	p37(10, 8);
	
	private int x;
	private int y;
	
	DefaultCoordinates(int x, int y) throws OutOfGrid {
		if (x < 0 || y < 0){
			throw new OutOfGrid("Coordinates cannot be negative.");
		}
		if (x >= 24 || y >= 24){
			throw new OutOfGrid("The point is outside the grid.");
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
        for (DefaultCoordinates coord: DefaultCoordinates.values()) {
            defaultPoints.add(Objects.hash(coord.getX(), coord.getY()));
        }
        return defaultPoints;
    }
}


