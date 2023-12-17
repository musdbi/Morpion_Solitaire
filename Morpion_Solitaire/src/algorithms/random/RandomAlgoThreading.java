package algorithms.random;

import java.util.ArrayList;
import java.util.List;

import algorithms.ResearchAlgorithm;

public class RandomAlgoThreading extends Thread{
	
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
		
	public RandomAlgoThreading(ResearchAlgorithm algo, int numberOfThreads, int iterations) {
		this.algo = algo;
		this.numberOfThreads = numberOfThreads;
		this.iterations = iterations;
		this.threadIterations = (int) Math.ceil(this.iterations / numberOfThreads);
	}
	
	@Override
	public void run() {
		algo.trainAlgorithm(this.iterations);
	}
	
	
	/**
	 * Execute the algorithm the given amount of times and divide in threads the task to gain time.
	 * 
	 * @throws InterruptedException
	 */
	public void trainAlgorithm() throws InterruptedException {
		long startTime = System.currentTimeMillis();
		
		List<RandomAlgoThreading> threads = new ArrayList<>(); // List to keep order of thread for .join()
		for(int i = 0; i < this.numberOfThreads; i++) {
			RandomAlgoThreading thread = new RandomAlgoThreading(algo, numberOfThreads, threadIterations);
			threads.add(thread);
			thread.start();
		}
		
		for(RandomAlgoThreading thread: threads) {
			thread.join();
		}

		long endTime = System.currentTimeMillis();
        double elapsedTime = (endTime - startTime) * 0.001;
        System.out.println("Time taken: " + elapsedTime + " seconds");
	}
	
	public static void main(String[] args) throws InterruptedException {
		RandomAlgoThreading randomMultiThread = new RandomAlgoThreading(new RandomAlgorithm(), 4, 50);
		randomMultiThread.trainAlgorithm();
		System.out.println("Nombres d'itérations : par thread " + randomMultiThread.threadIterations);
		System.out.println("Sur "+ randomMultiThread.iterations + " coups:");
		System.out.println("Number of threads: " + randomMultiThread.numberOfThreads);
	}
}