package algorithms;

import components.Grid;

public class NMCS extends ResearchAlgorithm{
	
	
	private final int depthLevel;
	
	public NMCS(int depthLevel) {
		this.depthLevel = depthLevel;
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