package algorithms;

import components.Grid;

public class NMCS implements ResearchAlgorithm{
	
	
	private final int depthLevel;
	
	public NMCS(int depthLevel) {
		this.depthLevel = depthLevel;
	}
	
	public NMCSState setUpInitState() {
		Grid initGrid = new Grid();
		initGrid.initGrid();
		initGrid.updatePlayablePoints();
		
		NMCSState initState = new NMCSState(null, initGrid);
		return initState;
	}

	@Override
	public int algorithm() {
		NMCSState initialState = setUpInitState();
		int score = NMCS(initialState, depthLevel);
		return score;
	}

	public int NMCS(NMCSState state, int level) {
		if (level == 0) {
			return state.scoreFromRollout();
		}
		
		state.exploreChilds();
		int bestScore = -1;
//		while (!state.isTerminal()) {
			for (NMCSState childState: state.getChilds()) {
				int currentChildScore = NMCS(childState, level -1);
				if (currentChildScore > bestScore ) bestScore = currentChildScore;
			}
//		}
		return bestScore;
	}
	@Override
	public void trainAlgorithm(int iterations) {
		// TODO Auto-generated method stub
	}
	
	public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

		NMCS nmcs0 = new NMCS(0);
		NMCS nmcs1 = new NMCS(1);
		NMCS nmcs2 = new NMCS(2);
		
		System.out.println("Score pour une recherch de profondeur 0: " + nmcs0.algorithm());	
		long endTime = System.currentTimeMillis();
		double elapsedTime = (endTime - startTime) * 0.001;
        System.out.println("Time taken: " + elapsedTime + " seconds");
        
		System.out.println("Score pour une recherch de profondeur 1: " + nmcs1.algorithm());
		endTime = System.currentTimeMillis();
		elapsedTime = (endTime - startTime) * 0.001;
        System.out.println("Time taken: " + elapsedTime + " seconds");
        
        System.out.println("Score pour une recherch de profondeur 2: " + nmcs2.algorithm());
		endTime = System.currentTimeMillis();
		elapsedTime = (endTime - startTime) * 0.001;
        System.out.println("Time taken: " + elapsedTime + " seconds");
	}
}