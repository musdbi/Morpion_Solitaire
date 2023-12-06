package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import components.Grid;
import components.Line;
import components.PlayedPoint;
import game.Mode;

public class RandomAlgorithm extends ResearchAlgorithm{
	
	private Random random;
	
	public RandomAlgorithm() {
		Mode.setNumber(5);
		Mode.setType("T");
		this.random = new Random();
	}
	
	public void algorithm() {
		Grid grid = setUpGrid();
		while (!grid.getPlayablePoints().isEmpty()){
	        this.playRandomMove(grid);
	        grid.updatePlayablePoints();
		}
//        grid.drawGrid();
		ResearchAlgorithm.addGrid(grid);
		ResearchAlgorithm.addScore(PlayedPoint.getCount());
	}
	
	public void trainAlgorithm(int iterations) {
		for (int i = 0; i < iterations; i++) {
			algorithm();
		}
	}
	
	public Grid setUpGrid() {
		PlayedPoint.resetCount();
		Grid grid = new Grid();
		grid.initGrid();
		grid.updatePlayablePoints();
		return grid;
	}
	
	public void playRandomMove(Grid grid){
		List<Line> lines = new ArrayList<>(grid.getPossibleMoves().keySet());
        Line randomLine = lines.get(random.nextInt(lines.size()));
        PlayedPoint randomPlayedPoint = new PlayedPoint(grid.getPossibleMoves().get(randomLine).getX(), grid.getPossibleMoves().get(randomLine).getY());
        grid.updateGrid(randomPlayedPoint, randomLine);
	}

	public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

		RandomAlgorithm randomAlgo = new RandomAlgorithm();
		int it = 100;
		randomAlgo.trainAlgorithm(it);
		ResearchAlgorithm.calculateStatistics();
		
		System.out.println("Sur " + it +  " coups:");
		System.out.println("Le score est en moyenne: " + mean);
		System.out.println("L'écart-type est: " + sigma);
		
		long endTime = System.currentTimeMillis();
		double elapsedTime = (endTime - startTime) * 0.001;
        System.out.println("Time taken: " + elapsedTime + " seconds");
	}
}