package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import components.Grid;
import components.Line;
import components.PlayedPoint;
import game.Mode;

public class RandomAlgorithm implements ResearchAlgorithm{
	
	private static final int algoId = 1;
	
	private Random random;
	
	/**
	 * The grid on which we are applying the algorithm
	 */
	private Grid grid;
	
	private static volatile List<Grid> grids = new ArrayList<Grid>();
	
	protected static volatile List<Integer> scores = new ArrayList<Integer>();
	
	protected static volatile double mean = 0.0;
	
	protected static volatile double var = 0.0;
	
	protected static volatile double sigma = 0.0;
	
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
		this.addGrid(simulatedGrid);	
		this.addScore(simulatedGrid.getLines().size());
		System.out.println("\nScore: " + simulatedGrid.getLines().size());
		DataManager.insertData(algoId, simulatedGrid.getLines().size());
		
		return simulatedGrid;
	}
	
	public void trainAlgorithm(int iterations) { 
		for (int i = 0; i < iterations; i++) {
			algorithm();
		}
	}

	public void playRandomMove(Grid grid){
		List<Line> lines = new ArrayList<>(grid.getPossibleMoves().keySet());
        Line randomLine = lines.get(random.nextInt(lines.size()));
        PlayedPoint randomPlayedPoint = new PlayedPoint(grid.getPossibleMoves().get(randomLine), grid.getLines().size() + 1);
        grid.updateGrid(randomPlayedPoint, randomLine);
	}
	
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
	
	public synchronized void addGrid(Grid grid) {
		grids.add(grid);
	}
	
	public synchronized void addScore(int score) {
		scores.add(score);
	}
	
	public static synchronized List<Grid> getGrid() {
		return grids;
	}
	
	public static synchronized List<Integer> getScores() {
		return scores;
	}
	
	public static synchronized double getMean() {
		return mean;
	}
	
	public static synchronized double getVar() {
		return var;
	}
	
	public static synchronized double getSigma() {
		return sigma;
	}
	
	public static int getId() {
		return algoId;
	}

	public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
//        
//        Mode.setNumber(5);
//        Mode.setType("T");

		ResearchAlgorithm randomAlgo = new RandomAlgorithm();
		int it = 50;
		randomAlgo.trainAlgorithm(it);
		RandomAlgorithm.calculateStatistics();
		
		System.out.println("Sur " + it +  " coups:");
		System.out.println("Le score est en moyenne: " + RandomAlgorithm.getMean());
		System.out.println("L'écart-type est: " + RandomAlgorithm.getSigma());
		
		long endTime = System.currentTimeMillis();
		double elapsedTime = (endTime - startTime) * 0.001;
        System.out.println("Time taken: " + elapsedTime + " seconds");
	}
}