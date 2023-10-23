package game;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Scoreboard {
	private final static String path = "./src/classement.txt";
	
	private TreeMap<Integer, String> scores;   
  
	public Scoreboard() {
		File fichier = new File(path);
		int car;
		StringBuffer contenu = new StringBuffer("");
		FileInputStream ftemp = null;
		try {
			ftemp = new FileInputStream(fichier);
			while ((car = ftemp.read()) != -1)
				contenu.append((char) car);
			ftemp.close();
	    }
	    catch(FileNotFoundException e) {
	      System.out.println("Fichier non trouvé, changez le path");
	    }
	    catch(IOException ioe) {
	      System.out.println("Exception " + ioe);
	    }
		scores = new TreeMap<>();
	}
	
	public void addScore(String playerName, int score) {
        scores.put(score, playerName);
    }

    public void write() {
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
    	    for (Entry<Integer, String> entry : scores.entrySet()) {
    	        writer.write(entry.getKey() + ":" + entry.getValue());
    	        writer.newLine(); // 
    	    }
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    }
}
