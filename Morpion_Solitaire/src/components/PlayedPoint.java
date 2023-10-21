package components;

public class PlayedPoint extends Point{
	
	/**
	 * serializing process ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Total number of played points
	 */
	private static int playedPointsCount;
	
	/**
	 * Id represent the order in which the current point was played
	 */
	private int id;
	
	public PlayedPoint(int x, int y) {
		super(x, y);
		playedPointsCount += 1;
		this.id = playedPointsCount;
	}
	
	public static void resetPlayedPointsCount() {
		playedPointsCount = 0;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getCount() {
		return playedPointsCount;
	}
}
