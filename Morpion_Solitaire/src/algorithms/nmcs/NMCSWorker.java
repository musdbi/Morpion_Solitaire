package algorithms.nmcs;

import java.util.concurrent.Callable;

import components.Grid;

public class NMCSWorker implements Callable<Grid>{
    private final NMCS nmcs;
	private final NMCSState childState;
    private final int level;
    private final long debut;
    private final long dureeMax;

    public NMCSWorker(NMCS nmcs,NMCSState childState, int level, long debut, long dureeMax) {
        this.nmcs = nmcs;
        this.childState = childState;
        this.level = level;
        this.debut = debut;
        this.dureeMax = dureeMax;
    }

	@Override
    public Grid call() {
        return nmcs.nmcs(childState, level, debut, dureeMax);
    }

}
