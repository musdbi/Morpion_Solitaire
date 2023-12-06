package algorithms;

import components.Grid;

public class NMCS extends ResearchAlgorithm{

	public NMCS() {
		// TODO Auto-generated constructor stub
	}
	
	public Grid setUpGrid() {
		Grid grid = new Grid();
		grid.initGrid();
		grid.updatePlayablePoints();
		return grid;
	}

	@Override
	void algorithm() {
		
	}

	@Override
	void trainAlgorithm(int iterations) {
		// TODO Auto-generated method stub
	}
}