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
	public Grid algorithm() {
		NMCSState initialState = setUpInitState();
		long debut = System.currentTimeMillis();
        long dureeMax = 1000000 * 1000; // 30 secondes en millisecondes
		return NMCS(initialState, depthLevel, debut, dureeMax);
	}

	public Grid NMCS(NMCSState state, int level, long debut, long dureeMax) {
		if (level == 0) {
			return state.rollout();
		}
		
		state.exploreChilds();
		Grid bestGrid = new Grid();
//		while (!state.isTerminal()) {
			for (NMCSState childState: state.getChilds()) {
				if (System.currentTimeMillis() - debut > dureeMax) {
	                break; // Sortir de la boucle si la durée est dépassée
	            }
				Grid currentChildGrid= NMCS(childState, level -1, debut, dureeMax);
				if (currentChildGrid.getLines().size() > bestGrid.getLines().size()) bestGrid = currentChildGrid;
			}
//		}
		return bestGrid;
	}
	@Override
	public void trainAlgorithm(int iterations) {
		// TODO Auto-generated method stub
	}
	
	public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        double endTime;
        double elapsedTime;
		NMCS nmcs0 = new NMCS(0);
		NMCS nmcs1 = new NMCS(1);
		NMCS nmcs2 = new NMCS(2);

		NMCS nmcs3 = new NMCS(3);
		NMCS nmcs4 = new NMCS(4);

//		Grid level0 = nmcs0.algorithm();
//        System.out.println("Score pour une recherch de profondeur 0: " + level0.getLines().size());
//		endTime = System.currentTimeMillis();
//		elapsedTime = (endTime - startTime) * 0.001;
//        System.out.println("Time taken: " + elapsedTime + " seconds");
//        System.out.println("Grid found: \n" + level0);
        
		Grid level1 = nmcs1.algorithm();
        System.out.println("Score pour une recherch de profondeur 1: " + level1.getLines().size());
		endTime = System.currentTimeMillis();
		elapsedTime = (endTime - startTime) * 0.001;
        System.out.println("Time taken: " + elapsedTime + " seconds");
        System.out.println("Grid found: \n" + level1);
        
//		Grid level2 = nmcs2.algorithm();
//        System.out.println("Score pour une recherch de profondeur 2: " + level2.getLines().size());
//		endTime = System.currentTimeMillis();
//		elapsedTime = (endTime - startTime) * 0.001;
//        System.out.println("Time taken: " + elapsedTime + " seconds");
//        System.out.println("Grid found: \n" + level2);
        
//        Grid level3 = nmcs3.algorithm();
//        System.out.println("Score pour une recherch de profondeur 3: " + level3.getLines().size());
//		endTime = System.currentTimeMillis();
//		elapsedTime = (endTime - startTime) * 0.001;
//        System.out.println("Time taken: " + elapsedTime + " seconds");
//        System.out.println("Grid found: \n" + level3);
	}
}