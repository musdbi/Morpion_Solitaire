package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Math;

import components.Grid;
import components.Line;
import components.PlayedPoint;
import components.Point;
import game.Mode;

public class RandomAlgorithm implements ResearchAlgorithm{
	
	Random random;
	
	private List<Grid> grids;
	
	private List<Integer> scores;
	
	private double mean;
	
	private double var;
	
	private double sigma;
	
	public RandomAlgorithm() {
		Mode.setNumber(5);
		Mode.setType("T");
		this.mean = 0;
		this.var = 0;
		this.sigma = 0;
		this.random = new Random();
		this.grids = new ArrayList<>();
		this.scores = new ArrayList<>();
	}
	
	public void launchAlgorithm() {
		Grid grid = setUpGrid();
		while (!grid.getPlayablePoints().isEmpty()){
	        PlayedPoint randomPlayedPoint = chooseRandomPoint(grid);
	        grid.updateGrid(randomPlayedPoint, chooseRandomLine(grid, randomPlayedPoint));
	        grid.updatePlayablePoints();
		}
//		grid.drawGrid();
		this.grids.add(grid);
		this.scores.add(PlayedPoint.getCount());
	}
	
	public void trainAlgorithm(int iterations) {
		for (int i = 0; i < iterations; i++) {
			launchAlgorithm();
		}
	}
	
	public Grid setUpGrid() {
		PlayedPoint.resetCount();
		Grid grid = new Grid();
		grid.initGrid();
		grid.updatePlayablePoints();
		return grid;
	}
	
	public PlayedPoint chooseRandomPoint(Grid grid) {
		List<Point> playablePoints = new ArrayList<>(grid.getPlayablePoints().keySet());
        Point randomPoint = playablePoints.get(random.nextInt(playablePoints.size()));
        return new PlayedPoint(randomPoint.getX(), randomPoint.getY());
	}
	
	public Line chooseRandomLine(Grid grid, PlayedPoint randomPlayedPoint) {
		List<Line> playableLines = new ArrayList<>(grid.getPlayablePoints().get(randomPlayedPoint));
        return playableLines.get(random.nextInt(playableLines.size()));
	}
	
	public void calculateStatistics() {
		int sum = 0;
	    for (int score : this.scores) {
	        sum += score;
	    }
	    this.mean =  ((double) sum / this.scores.size());
	    

	    double squareSum = 0;
	    
	    for (int nombre : this.scores) {
	        squareSum += Math.pow(nombre - this.mean, 2);
	    }
	    this.var =  squareSum / this.scores.size();
	    
	   this.sigma = Math.sqrt(var);
	}
	
	public static void main(String[] args) {
		RandomAlgorithm randomAlgo = new RandomAlgorithm();
//		int score = randomAlgo.launchAlgorithm();
//		System.out.println("Le score de l'algorithme aléatoire est de " + score + " coups");
		int it = 100;
		randomAlgo.trainAlgorithm(it);
		randomAlgo.calculateStatistics();
		System.out.println("Sur " + it +  " coups:");
		System.out.println("Le score est en moyenne: " + randomAlgo.mean);
		System.out.println("L'écart-type est: " + randomAlgo.sigma);
	}
}
