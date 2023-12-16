package algorithms.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algorithms.DataManager;
import algorithms.ResearchAlgorithm;
import components.Grid;
import components.Line;
import components.PlayedPoint;
import game.Mode;

public class RandomAlgorithm implements ResearchAlgorithm{

	private Random random;
	
	/**
	 * The grid on which we are applying the algorithm
	 */
	private Grid grid;
	
	public RandomAlgorithm() {
		this.random = new Random();
		this.grid = new Grid();
		this.grid.initGrid();
		this.grid.updatePlayablePoints();
	}
	
	/**
	 * Constructor used in NMCS algorithm to start the random search from a specifi grid
	 * 
	 * @param modeNumber
	 * @param modeType
	 * @param grid
	 */
	public RandomAlgorithm(Grid grid) {
		this.grid = new Grid(grid);
		this.random = new Random();
	}
	
	@Override
	public Grid algorithm() {
		Grid simulatedGrid = new Grid(this.grid);
		while (!simulatedGrid.getPlayablePoints().isEmpty()){
	        playRandomMove(simulatedGrid);
	        simulatedGrid.updatePlayablePoints();
		}
		
		// Saving score in csv file
		if (DataManager.getCurrRunningAlgo() == 0) {
			DataManager.insertData(
					DataManager.getCurrRunningAlgo(),
					"" + Mode.getNumber() + Mode.getType(),
					simulatedGrid.getLines().size());
		}
		
		return simulatedGrid;
	}
	
	/**
	 * Play a random point and a random line (if there is the choice) on the given grid
	 *
	 * @param grid
	 */
	public void playRandomMove(Grid grid){
		List<Line> lines = new ArrayList<>(grid.getPossibleMoves().keySet());
        Line randomLine = lines.get(random.nextInt(lines.size()));
        PlayedPoint randomPlayedPoint = new PlayedPoint(grid.getPossibleMoves().get(randomLine), grid.getLines().size() + 1);
        grid.updateGrid(randomPlayedPoint, randomLine);
	}

	public void trainAlgorithm(int iterations) { 
		for (int i = 0; i < iterations; i++) {
			grid = algorithm();
		}
	}

	public Grid getGrid() {
		return grid;
	}

	public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        DataManager.setCurrRunningAlgo(0);
        ResearchAlgorithm randomAlgo = new RandomAlgorithm();
		int it = 50;
		randomAlgo.trainAlgorithm(it);
		long endTime = System.currentTimeMillis();
		double elapsedTime = (endTime - startTime) * 0.001;
        System.out.println("Time taken: " + elapsedTime + " seconds");
	}
}