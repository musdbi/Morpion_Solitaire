package algorithms;

import components.Grid;
import components.PlayedPoint;

public class NMCS extends ResearchAlgorithm{

	public NMCS() {
		// TODO Auto-generated constructor stub
	}
	
	public Grid setUpGrid() {
		PlayedPoint.resetCount();
		Grid grid = new Grid();
		grid.initGrid();
		grid.updatePlayablePoints();
		return grid;
	}

	@Override
	void algorithm() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void trainAlgorithm(int iterations) {
		// TODO Auto-generated method stub
	}
}