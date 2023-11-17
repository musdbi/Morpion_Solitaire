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

public class RandomAlgorithm extends ResearchAlgorithm{
	
	private Random random;
	
	public RandomAlgorithm() {
		Mode.setNumber(5);
		Mode.setType("T");
		this.random = new Random();
	}
	
	public void launchAlgorithm() {
		Grid grid = setUpGrid();
		while (!grid.getPlayablePoints().isEmpty()){
	        PlayedPoint randomPlayedPoint = chooseRandomPoint(grid);
	        grid.updateGrid(randomPlayedPoint, chooseRandomLine(grid, randomPlayedPoint));
	        grid.updatePlayablePoints();
		}
//		grid.drawGrid();
		ResearchAlgorithm.addGrid(grid);
		ResearchAlgorithm.addScore(PlayedPoint.getCount());
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
	
	public static void main(String[] args) {
		RandomAlgorithm randomAlgo = new RandomAlgorithm();
//		int score = randomAlgo.launchAlgorithm();
//		System.out.println("Le score de l'algorithme aléatoire est de " + score + " coups");
		int it = 50;
		randomAlgo.trainAlgorithm(it);
		ResearchAlgorithm.calculateStatistics();
		System.out.println("Sur " + it +  " coups:");
		System.out.println("Le score est en moyenne: " + mean);
		System.out.println("L'écart-type est: " + sigma);
	}
}
