package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	
	/**
	 * The number of times one thread will launch the algorithm
	 * 
	 * = math.ceil(this.iterations / numberOfThreads)
	 * 
	 * We round up the result 
	 */
	private int threadIterations;
		
	public MultiThreading(ResearchAlgorithm algo, int numberOfThreads, int iterations) {
		this.algo = algo;
		this.numberOfThreads = numberOfThreads;
		this.iterations = iterations;
		this.threadIterations = (int) Math.ceil(this.iterations / numberOfThreads);
	}
	
	@Override
	public void run() {
		algo.trainAlgorithm(this.iterations);
	}
	
	public void trainAlgorithm() throws InterruptedException {
		long startTime = System.currentTimeMillis();
		
		List<MultiThreading> threads = new ArrayList<>(); // List to keep order of thread for .join()
		for(int i = 0; i < this.numberOfThreads; i++) {
			MultiThreading thread = new MultiThreading(algo, numberOfThreads, threadIterations);
			threads.add(thread);
			thread.start();
		}
		
		for(MultiThreading thread: threads) {
			thread.join();
		}

		long endTime = System.currentTimeMillis();
        double elapsedTime = (endTime - startTime) * 0.001;
        System.out.println("Time taken: " + elapsedTime + " seconds");
	}
	
	public static void main(String[] args) throws InterruptedException {
		MultiThreading randomMultiThread = new MultiThreading(new RandomAlgorithm(), 4, 552);
		randomMultiThread.trainAlgorithm();
		RandomAlgorithm.calculateStatistics();
		System.out.println("Nombres d'itérations au total: " + RandomAlgorithm.getScores().size());
		System.out.println("Nombres d'itérations : par thread " + randomMultiThread.threadIterations);
		System.out.println("Sur "+ randomMultiThread.iterations + " coups:");
		System.out.println("Number of threads: " + randomMultiThread.numberOfThreads);;
		System.out.println("Le score est en moyenne: " + RandomAlgorithm.getMean());
		System.out.println("L'écart-type est: " + RandomAlgorithm.getSigma());
	}
}
