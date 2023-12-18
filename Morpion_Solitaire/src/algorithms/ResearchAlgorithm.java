 package algorithms;


import components.Grid;

public interface  ResearchAlgorithm {
	
	/**
	 * Launch algorithm one time 
	 * @return the result grid from the concerned algorithm
	 */
	Grid algorithm();
	
	/**
	 * @param iterations: Represent the number of times we want to launch the algorithm
	 * @return 
	 */
	void trainAlgorithm(int iterations);
}
