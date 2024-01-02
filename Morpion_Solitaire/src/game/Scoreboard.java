package game;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Scoreboard {
	
	private final static String path = "./data/scoreboard.txt";
	
	private TreeMap<Integer, String> scores;   
	
	/**
     * Constructs a Scoreboard object by initializing the scores TreeMap and reading scores from a file.
     */
  
	public Scoreboard() {
		File file = new File(path);
		int car;
		StringBuffer content = new StringBuffer("");
		FileInputStream ftemp = null;
		try {
			ftemp = new FileInputStream(file);
			while ((car = ftemp.read()) != -1)
				content.append((char) car);
			ftemp.close();
	    } catch(IOException ioe) {
	      System.out.println("Exception " + ioe);
	    }
		scores = new TreeMap<>(Collections.reverseOrder());
	}
	
	/**
     * Clears all scores from the scoreboard.
     */
	
	public void clear () {
		scores.clear();
	}
	
	/**
     * Opens the scoreboard file in the default file viewer of the desktop.
     */
	
	public void show() {
	    if (Desktop.isDesktopSupported()) {
	        try {
	            File file = new File(path);
	            Desktop.getDesktop().open(file);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        System.out.println("Desktop is not supported");
	    }
	}
	
	/**
     * Adds a score for a player to the scoreboard.
     *
     * @param playerName The name of the player.
     * @param score The score of the player.
     * @throws IllegalArgumentException if the player name is empty or score is negative.
     */
	
	public void addScore(String playerName, int score) {
		if (playerName.isEmpty()) throw new IllegalArgumentException("Please type a valid name.");
		if (score < 0) throw new IllegalArgumentException("Score cannot be negative..");
        scores.put(score, playerName);
    }
	
	/**
     * Writes the scores to the file, overwriting any existing content.
     */

    public void write() {
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
    	    for (Entry<Integer, String> entry : scores.entrySet()) {
				writer.write(entry.getKey() + " played points - " + entry.getValue());
    	        writer.newLine();
    	        writer.newLine();
    	    }
    	    writer.close();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    }
    
    /**
     * Appends the current scores to the end of the file.
     */
    
    public void append() {
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(path,true))) {
    	    for (Entry<Integer, String> entry : scores.entrySet()) {
    	        writer.write(entry.getKey() + " played points - " + entry.getValue());
    	        writer.newLine();
    	        writer.newLine();
    	    }
    	    writer.close();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    }
    
    /**
     * Sorts the scores in the file based on score values.
     */
    
    public void sort() {
    	try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            List<ScoreTuple> uniqueCombo = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    try (Scanner scanner = new Scanner(line)) {
                        scanner.useDelimiter(" played points - ");
                        if (scanner.hasNextInt()) {
                            int recordedScore = scanner.nextInt();
                            if (scanner.hasNext()) {
                                String name = scanner.next();
                                uniqueCombo.add(new ScoreTuple(recordedScore, name));
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error processing line: " + line);
                        e.printStackTrace();
                    }
                }
            }
            reader.close();
            Collections.sort(uniqueCombo, new TupleComparator());
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
    		for (ScoreTuple entry : uniqueCombo) {
    	        writer.write(entry.getScore() + " played points - " + entry.getName());
    	        writer.newLine();
    	        writer.newLine();
    	    }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main (String [] args){
//    	Scoreboard sb = new Scoreboard ();
//    	sb.addScore("Mus", 1);
//    	sb.addScore("Mus", 2);
//    	sb.addScore("Mus", 3);
//    	sb.addScore("Mus", 4);
//    	sb.addScore("Mus", 5);
//    	sb.addScore("Mus", 6);
//    	sb.addScore("Mus", 7);
//    	sb.addScore("Pierre", 117);
//    	sb.write();
//    	sb.sort();
    }
}
