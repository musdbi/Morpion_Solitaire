package helpers;

import java.util.ArrayList;
import java.util.List;

import game.Mode;

public enum Orientation {
	N(0,1),		// North - top
	S(0,-1), 	// South - bottom
	W(-1,0), 	// West - left
	E(1,0), 	// East - right
	NW(-1,1), 	// North West = top left
	NE(1,1), 	// North East = top right
	SW(-1,-1), 	// South West = bottom left
	SE(1, -1); 	// South East = bottom right
	
	
	private int x;
    private int y;
    
	Orientation(int x, int y) {
        this.x = x;
        this.y = y;
    }
	
	public List<Integer> moveX(){
		List<Integer> moveX = new ArrayList<>();
		for (int i = 1; i <= Mode.getNumber() - 1; i++) {
			moveX.add(this.x * i);
		}
		return moveX;
	}
	
	public List<Integer> moveY(){
		List<Integer> moveY = new ArrayList<>();
		for (int i = 1; i <= Mode.getNumber() - 1; i++) {
			moveY.add(this.y * i);
		}
		return moveY;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public static void main(String[] args) {
//		System.out.println(S.moveX());
//		System.out.println(S.moveY());
//		List<Integer> moveY = S.moveY();
//		Collections.reverse(moveY);
//		System.out.println(moveY);

	}
}