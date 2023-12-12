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

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

/**
 * This class is used to save scores of the algorithms by inserting them in scores.csv file
 */
public class DataManager {
	
	private static int currRunningAlgoId;
	
	private static final String path = "./algo_scores.csv";

	/**
	 * This class insert new line in the scores.csv file
	 */
	public static synchronized void insertData(int algoId, String mode, int score){
		try (CSVWriter writer = new CSVWriter(new FileWriter(path, true))) {
            String[] data = {String.valueOf(algoId), String.valueOf(mode), String.valueOf(score)};
            writer.writeNext(data);
            System.out.println("Data successfully inserted: " + algoId + ", " + mode + ", " + score);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static List<Integer> getData(int algoId, String mode) throws CsvValidationException, NumberFormatException {
		List<Integer> scores = new ArrayList<>();
		System.out.println("1");
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
            	// If the line is not empty
        		if (!line[0].equals("")){
        			int fileAlgoId = Integer.parseInt(line[0]);
                    String fileMode = line[1];
                    if (fileAlgoId == algoId && mode.equals(fileMode)) {
                        int score = Integer.parseInt(line[2]);
                        scores.add(score);
                    }
        		}
                
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
//        catch(NumberFormatException e2) {
//        }
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
	 * @throws NumberFormatException 
	 * @throws CsvValidationException 
	 */
	public static Map<String, Double> calculateStatistics(int algoId, String mode) throws CsvValidationException, NumberFormatException {
		Map<String, Double> statistics = new HashMap<>();
        List<Integer> scores = getData(algoId, mode);

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
	
	public static void setCurrRunningAlgo(int id) {
		if (id != 0 && id !=1 && id != 2 && id != 3) throw new IllegalArgumentException("Id must be 1, 2 or 3");
		currRunningAlgoId = id;
	}
	
	public static int getCurrRunningAlgo() {
		return currRunningAlgoId;
	}
	
	public static void main(String[] args) throws CsvValidationException, NumberFormatException {
		System.out.println(DataManager.calculateStatistics(0, "5T"));
		System.out.println(DataManager.calculateStatistics(1, "5T"));
		System.out.println(DataManager.calculateStatistics(2, "5T"));
	}
}
