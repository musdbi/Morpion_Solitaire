package algorithms;

import java.util.ArrayList;
import java.util.List;

import components.Grid;

public abstract class ResearchAlgorithm {
	
	private static List<Grid> grids = new ArrayList<>();
	
	protected static List<Integer> scores = new ArrayList<>();
	
	protected static double mean = 0.0;
	
	protected static double var = 0.0;
	
	protected static double sigma= 0.0;
	
	abstract Grid setUpGrid();
	
	abstract void launchAlgorithm();
	
	abstract void trainAlgorithm(int iterations);
	
	public static void calculateStatistics() {
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

	public static void addGrid(Grid grid) {
		grids.add(grid);
	}
	
	public static void addScore(int score) {
		scores.add(score);
	}
}
