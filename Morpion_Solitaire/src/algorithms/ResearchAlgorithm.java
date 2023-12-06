package algorithms;

import java.util.ArrayList;
import java.util.List;

import components.Grid;

public abstract class ResearchAlgorithm {
	
	private volatile static List<Grid> grids = new ArrayList<>();
	
	protected volatile static List<Integer> scores = new ArrayList<>();
	
	protected volatile static double mean = 0.0;
	
	protected volatile static double var = 0.0;
	
	protected volatile static double sigma= 0.0;
	
	public abstract Grid setUpGrid();
	/**
	 * Launch algorithm one time
	 */
	abstract void algorithm();
	
	/**
	 * @param iterations: Represent the number of times we want to launch the algorithm
	 */
	abstract void trainAlgorithm(int iterations);
	
	public static synchronized void calculateStatistics() {
		int sum = 0;
	    for (int score : scores) {
	        sum += score;
	    }
	    mean =  ((double) sum / scores.size());
	    

	    double squareSum = 0;
	    
	    for (int nombre : scores) {
	        squareSum += Math.pow(nombre - mean, 2);
	    }
	    var =  squareSum / scores.size();
	    
	   sigma = Math.sqrt(var);
	}

	public static synchronized void addGrid(Grid grid) {
		grids.add(grid);
	}
	
	/**
	 * Method to add scores while ensuring thread safety
	 * 
	 * @param score
	 */
	public static synchronized void addScore(int score) {
		scores.add(score);
	}
	
	public static double getMean() {
		return mean;
	}
	
	public static double getSigma() {
		return sigma;
	}
	
	public static List<Integer> getScores(){
		return scores;
	}
}
