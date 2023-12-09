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
		long debut = System.currentTimeMillis();
        long dureeMax = 1000000 * 1000; // 30 secondes en millisecondes
		int score = NMCS(initialState, depthLevel, debut, dureeMax);
		return score;
	}

	public int NMCS(NMCSState state, int level, long debut, long dureeMax) {
		if (level == 0) {
			return state.scoreFromRollout();
		}
		
		state.exploreChilds();
		int bestScore = -1;
//		while (!state.isTerminal()) {
			for (NMCSState childState: state.getChilds()) {
				if (System.currentTimeMillis() - debut > dureeMax) {
	                break; // Sortir de la boucle si la durée est dépassée
	            }
				int currentChildScore = NMCS(childState, level -1, debut, dureeMax);
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

		NMCS nmcs3 = new NMCS(3);
		NMCS nmcs4 = new NMCS(4);

//		
//		System.out.println("Score pour une recherch de profondeur 0: " + nmcs0.algorithm());	
//		long endTime = System.currentTimeMillis();
//		double elapsedTime = (endTime - startTime) * 0.001;
//        System.out.println("Time taken: " + elapsedTime + " seconds");
//        
//		System.out.println("Score pour une recherch de profondeur 1: " + nmcs1.algorithm());
//		endTime = System.currentTimeMillis();
//		elapsedTime = (endTime - startTime) * 0.001;
//        System.out.println("Time taken: " + elapsedTime + " seconds");
        
        System.out.println("Score pour une recherch de profondeur 2: " + nmcs2.algorithm());
		double endTime = System.currentTimeMillis();
		double elapsedTime = (endTime - startTime) * 0.001;
        System.out.println("Time taken: " + elapsedTime + " seconds");
        
//        System.out.println("Score pour une recherch de profondeur 3: " + nmcs3.algorithm());
//		endTime = System.currentTimeMillis();
//		elapsedTime = (endTime - startTime) * 0.001;
//        System.out.println("Time taken: " + elapsedTime + " seconds");
//        
//        System.out.println("Score pour une recherch de profondeur 4: " + nmcs4.algorithm());
//		endTime = System.currentTimeMillis();
//		elapsedTime = (endTime - startTime) * 0.001;
//        System.out.println("Time taken: " + elapsedTime + " seconds");
	}
}