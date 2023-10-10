package components;

public class PlayedPoint extends Point{
	
	private static int count;
	
	/**
	 * The id is the order in which the point was played
	 */
	private int id;
	
	/**
	 * the direction of the line the point created
	 */
	private Direction direction;
	
	public PlayedPoint(Point p, Direction direction) {
		super(p.getX(), p.getY());
		this.id = this.count;
		this.count += 1;
	}

}
