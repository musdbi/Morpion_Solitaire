package algorithms;

public class MultiThreading extends Thread{
	
	@Override
	public void run() {
		RandomAlgorithm randomAlgo = new RandomAlgorithm();
		randomAlgo.trainAlgorithm(34);
	}
	
	public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
		MultiThreading thread1 = new MultiThreading();
		MultiThreading thread2 = new MultiThreading();
		MultiThreading thread3 = new MultiThreading();

		thread1.start();
		thread2.start();
		thread3.start();
		
		thread1.join();
		thread2.join();
		thread3.join();

		
		System.out.println("Nombres d'itérations au total: " + ResearchAlgorithm.getScores().size());
		
		ResearchAlgorithm.calculateStatistics();
		System.out.println("Sur 100 coups:");
		System.out.println("Le score est en moyenne: " + ResearchAlgorithm.getMean());
		System.out.println("L'écart-type est: " + ResearchAlgorithm.getSigma());
		
        long endTime = System.currentTimeMillis();
        double elapsedTime = (endTime - startTime) * 0.001;
        System.out.println("Time taken: " + elapsedTime + " seconds");

	}
}
