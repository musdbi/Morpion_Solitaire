package algorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to save scores of the algorithms by inserting them in scores.csv file
 */
public class DataManager {
	
	private static int currRunningAlgoId = 0;;
	
	private static final String path = "D:\\git-repositories\\Morpion_Solitaire\\Morpion_Solitaire\\src\\algorithms\\test.txt";
	
	/**
	 * Separator for csv file
	 */
	private static char sep = ';';
	
	/**
	 * This class insert new line in the scores.csv file
	 */
	public static void insertData(int algoId, int score){
		 try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
			 System.out.println("Line inserted: " + algoId + sep + score + System.lineSeparator());
	            String line = algoId + " " + score + System.lineSeparator();
	            writer.write(line);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	public static List<Integer> getData(int algoId) {
		List<Integer> scores = new ArrayList<>();

	    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] data = line.split(String.valueOf(" "));
	            if (data.length == 2) { // avoiding potentials errors in lines
	                int fileAlgoId = Integer.parseInt(data[0].trim()); // trim cleans spaces and etc before parsing in integer
	                if (fileAlgoId == algoId) {
	                    int score = Integer.parseInt(data[1].trim());
	                    scores.add(score);
	                }
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return scores;
	}
	
	/**
	 * 
	 * This method calculates basic statistics with the data of the csv file for the given algo
	 * 
	 * @param algoId, the id of the algo on which we want to calculate statistics
	 * @return a map with as keys, values: 
	 * 			"Mean": the mean 
	 * 			"Variance": the calculated variance
	 * 			"Sigma": the  standard deviation (Ecart-Type in french)
	 */
	public static Map<String, Double> calculateStatistics(int algoId) {
		Map<String, Double> statistics = new HashMap<>();
        List<Integer> scores = getData(algoId);

        // Mean
        double sum = 0;
        for (int score : scores) {
            sum += score;
        }
        double mean = sum / scores.size();
        statistics.put("Mean", mean);

        // Variance
        double variance = 0;
        for (int score : scores) {
            variance += Math.pow(score - mean, 2);
        }
        variance /= scores.size();
        statistics.put("Variance", variance);

        // Sigma
        statistics.put("Sigma", Math.sqrt(variance));

        return statistics;
	}
	
	public void setCurrRunningAlgo(int id) {
		if (id != 0 && id !=1) throw new IllegalArgumentException("Id must be 1 or 2");
		currRunningAlgoId = id;
	}
	
	public static void main(String[] args) {
		System.out.println(DataManager.calculateStatistics(1));
	}
}
