 package algorithms;


import components.Grid;

public interface  ResearchAlgorithm {
	
	/**
	 * Launch algorithm one time
	 */
	void algorithm();
	
	/**
	 * @param iterations: Represent the number of times we want to launch the algorithm
	 */
	void trainAlgorithm(int iterations);
}
