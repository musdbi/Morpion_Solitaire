package components;

import java.util.ArrayList;
import java.util.List;

public enum Orientation {
	N(0,1),		// North - top
	S(0,-1), 	// South - bottom
	W(-1,0), 	// West - left
	E(1,0), 	// East - right
	NW(-1,1), 	// North West = top left
	NE(1,1), 	// North East = top right
	SW(-1,-1), 	// South West = bottom left
	SE(-1,1); 	// South East = bottom right
	
	
	private int x;
    private int y;
    
	Orientation(int x, int y) {
        this.x = x;
        this.y = y;
    }
	
	public List<Integer> moveX(){
		ArrayList<Integer> moveX = new ArrayList<>();
		for (int x = 1; x <= 4; x++) {
			moveX.add(x * this.x);
		}
		return moveX;
	}
	
	public List<Integer> moveY(){
		ArrayList<Integer> moveY = new ArrayList<>();
		for (int y = 1; y <= 4; x++) {
			moveY.add(y * this.y);
		}
		return moveY;
	}
}