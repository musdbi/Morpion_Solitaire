package algorithms;

import java.util.Map;
import java.util.Set;

import components.Grid;
import components.Point;
import components.Line;

public class NMCSState {
	
	
	/**
	 * The grid of the current state, i.e the current state of the game
	 */
	private Grid currentStateGrid;
	
	
	/**
	 * The score if we launch random algorithm from this state.
	 */
	private int scoreFromRollout;
	
	
	private Map<Line, Point> possibleMoves;
	
	/**
	 *  The previous state in monte-carlo tree search, i.e one play before this one
	 */
	private NMCSState parentState;
	
	
	/**
	 * The child states, i.e the states with moves executed choosen in the possible moves of the current state
	 */
	private Set<NMCSState> childStates;

	public NMCSState() {
		
	}
	
	
}
