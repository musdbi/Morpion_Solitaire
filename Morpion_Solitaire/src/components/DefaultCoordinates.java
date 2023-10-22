package components;

import java.util.HashSet;
import java.util.Objects;

public enum DefaultCoordinates {
	p1(9, 12),
	p2(9, 13),
	p3(9, 14),
	p4(9, 15),
	p5(10, 12),
	p6(10, 15),
	p7(11, 12),
	p8(11, 15),
	p9(12, 9),
	p10(12, 10),
	p12(12, 11),
	p13(12, 12),
	p14(12, 15),
	p15(12, 16),
	p16(12, 17),
	p17(12, 18),
	p18(13, 9),
	p19(13, 18),
	p20(14, 9),
	p21(14, 18),
	p22(15, 9),
	p23(15, 10),
	p24(15, 11),
	p25(15, 12),
	p26(15, 15),
	p27(15, 16),
	p28(15, 17),
	p29(15, 18),
	p30(16, 12),
	p31(16, 15),
	p32(17, 12),
	p33(17, 15),
	p34(18, 12),
	p35(18, 13),
	p36(18, 14),
	p37(18, 15);
	
	private int x;
	private int y;
	
	DefaultCoordinates(int x, int y) {
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
	

	public static void main(String[] args) {
		for (int x = 0; x < 24; x++) {
			for(int y = 0; y < 24; y++) {
				if (DefaultCoordinates.getValues().contains(Objects.hash(x, y))) {
					System.out.println("(" + x + ", " + y +")");
				}
			}
		}
	}
}


