package algorithms.nmcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import algorithms.random.RandomAlgorithm;
import components.Grid;
import components.Point;
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
	 * i.e play every possible moves of the current state grid and create states from the new grids
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
	
	public Grid rollout() {
		RandomAlgorithm randomAlgo = new RandomAlgorithm(this.currentStateGrid);
		return randomAlgo.algorithm();

	}
	
	public boolean isTerminal() {
		return currentStateGrid.getPlayablePoints().isEmpty();
	}
	
	public Grid getGrid(){
		return currentStateGrid;
	}
	
	public List<NMCSState> getChilds(){
		return this.childStates;
	}
	
	
	public static void main(String[] args) {
		Grid grid = new Grid();
		grid.initGrid();
		grid.updatePlayablePoints();
		NMCSState initState = new NMCSState(null, grid);
		initState.exploreChilds();
		
		System.out.println(initState.currentStateGrid);
		for(NMCSState childState: initState.getChilds()) {
			System.out.println(childState.getGrid());
		}
	}
}
