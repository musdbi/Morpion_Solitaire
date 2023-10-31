package helpers;

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
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}