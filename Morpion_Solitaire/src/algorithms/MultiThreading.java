package algorithms;

public class MultiThreading extends Thread{
	
	/**
	 * The research algorithm we are training
	 */
	private ResearchAlgorithm algo;
	
	/**
	 * How many threads we want to create 
	 */
	private int numberOfThreads;
	
	/**
	 * Represent the number of times we want to launch the algorithm
	 */
	private int iterations;
	
	public MultiThreading(ResearchAlgorithm algo, int numberOfThreads, int iterations) {
		this.algo = algo;
		this.numberOfThreads = numberOfThreads;
		this.iterations = iterations;
	}
	
	@Override
	public void run() {
		algo.trainAlgorithm(this.iterations);
	}
	
	public void trainAlgorithm() throws InterruptedException {
		long startTime = System.currentTimeMillis();
		MultiThreading thread1 = new MultiThreading(algo, numberOfThreads, iterations);
		MultiThreading thread2 = new MultiThreading(algo, numberOfThreads, iterations);
		MultiThreading thread3 = new MultiThreading(algo, numberOfThreads, iterations);

		thread1.start();
		thread2.start();
		thread3.start();
		
		thread1.join();
		thread2.join();
		thread3.join();
		
		long endTime = System.currentTimeMillis();
        double elapsedTime = (endTime - startTime) * 0.001;
        System.out.println("Time taken: " + elapsedTime + " seconds");
	}
	
	public static void main(String[] args) throws InterruptedException {
//        long startTime = System.currentTimeMillis();
//        MultiThreading thread1 = new MultiThreading(new RandomAlgorithm(), 3, 100);
//		MultiThreading thread2 = new MultiThreading(new RandomAlgorithm(), 3, 100);
//		MultiThreading thread3 = new MultiThreading(new RandomAlgorithm(), 3, 100);
//
//		thread1.start();
//		thread2.start();
//		thread3.start();
//		
//		thread1.join();
//		thread2.join();
//		thread3.join();
//		
//        long endTime = System.currentTimeMillis();
//        double elapsedTime = (endTime - startTime) * 0.001;
//        System.out.println("Time taken: " + elapsedTime + " seconds");
		
		MultiThreading randomMultiThread = new MultiThreading(new RandomAlgorithm(), 3, 34);
		randomMultiThread.trainAlgorithm();
		System.out.println("Nombres d'itérations au total: " + ResearchAlgorithm.getScores().size());
		ResearchAlgorithm.calculateStatistics();
		System.out.println("Sur 100 coups:");
		System.out.println("Le score est en moyenne: " + ResearchAlgorithm.getMean());
		System.out.println("L'écart-type est: " + ResearchAlgorithm.getSigma());
	}
}
