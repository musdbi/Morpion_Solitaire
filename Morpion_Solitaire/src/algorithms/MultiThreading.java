package algorithms;

public class MultiThreading extends Thread{
	
	@Override
	public void run() {
		RandomAlgorithm randomAlgo = new RandomAlgorithm();
		randomAlgo.trainAlgorithm(100);
		randomAlgo.calculateStatistics();
	}
	
	public static void main(String[] args) {
		MultiThreading trainingAlgo = new MultiThreading();
		
		
	}
}
