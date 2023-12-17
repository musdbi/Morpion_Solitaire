package algorithms.nmcs;

import java.util.ArrayList;
import java.util.List;

import algorithms.random.RandomAlgorithm;
import components.Grid;
import components.Line;
import components.PlayedPoint;

public class NMCSState {
	
	
	/**
	 * The grid of the current state, i.e the current state of the game
	 */
	private Grid currentStateGrid;
			
	/**
	 *  The previous state in monte-carlo tree search, i.e one play before this one
	 */
	private NMCSState parentState;
	
	
	/**
	 * The child states, i.e the states with moves choosen in the possible moves of the current state
	 */
	private List<NMCSState> childStates;

	public Object getCChilds;

	public NMCSState(NMCSState parentState, Grid grid) {
		this.currentStateGrid = new Grid(grid);
		this.parentState = parentState;
		this.childStates = new ArrayList<NMCSState>();
	}
		
	/**
	 * Explore the child states of the current state.
	 * i.e create one new state for every possible move
	 */
	public void exploreChilds(){
		if (!this.currentStateGrid.getPossibleMoves().isEmpty()) {
			for(Line playedLine: this.currentStateGrid.getPossibleMoves().keySet()) {
				Grid childStateGrid = new Grid(currentStateGrid);
				PlayedPoint playedPoint = new PlayedPoint(
						this.currentStateGrid.getPossibleMoves().get(playedLine),
						this.currentStateGrid.getLines().size() + 1
						);
				childStateGrid.updateGrid(playedPoint, playedLine);
				this.childStates.add(new NMCSState(this, childStateGrid));
			}
		}
	}
	
	
	/**
	 * @return the result of a random algorithm starting at the current child grid
	 */
	public Grid rollout() {
		RandomAlgorithm randomAlgo = new RandomAlgorithm(this.currentStateGrid);
		return randomAlgo.algorithm();

	}
	
	
	/**
	 * @return true / false if the state has no possible child i.e if we can't play any more move
	 */
	public boolean isTerminal() {
		return currentStateGrid.getPlayablePoints().isEmpty();
	}
	
	public Grid getGrid(){
		return currentStateGrid;
	}
	
	public List<NMCSState> getChilds(){
		return this.childStates;
	}
}
