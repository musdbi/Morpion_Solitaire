package game;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import game.ScoreTuple;

public class Scoreboard {
	private final static String path = "./classement.txt";
	
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
	    } catch(IOException ioe) {
	      System.out.println("Exception " + ioe);
	    }
		scores = new TreeMap<>(Collections.reverseOrder());
	}
	
	public void addScore(String playerName, int score) {
        scores.put(score, playerName);
    }

    public void write() {
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
    	    for (Entry<Integer, String> entry : scores.entrySet()) {
    	        writer.write(entry.getKey() + " points joués - " + entry.getValue());
    	        writer.newLine();
    	        writer.newLine();
    	    }
    	    writer.close();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    }
    
    public void append() {
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(path,true))) {
    	    for (Entry<Integer, String> entry : scores.entrySet()) {
    	        writer.write(entry.getKey() + " points joués - " + entry.getValue());
    	        writer.newLine();
    	        writer.newLine();
    	    }
    	    writer.close();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    }
    
    public void sort() {
    	try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            List<ScoreTuple> uniqueCombo = new ArrayList<>();
            String ligne;
            while ((ligne = reader.readLine()) != null) {
            	if (!ligne.isEmpty()) {
            		int recordedScore = Integer.parseInt(ligne.substring(0,1));
                    uniqueCombo.add(new ScoreTuple (recordedScore, ligne.substring(ligne.lastIndexOf(" ") + 1)));
            	}
            }
            reader.close();
            
            Collections.sort(uniqueCombo, new TupleComparator());
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write("Tableau des scores :");
    		writer.newLine();
    		writer.newLine();
    		writer.write("--------------------------------------------------------");
    		writer.newLine();
    		writer.newLine();
    		for (ScoreTuple entry : uniqueCombo) {
    	        writer.write(entry.getFirst() + " points joués - " + entry.getSecond());
    	        writer.newLine();
    	        writer.newLine();
    	    }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main (String [] args){
    	Scoreboard sb = new Scoreboard ();
    	sb.addScore("Mus", 1);
    	sb.addScore("Mus", 2);
    	sb.addScore("Mus", 3);
    	sb.addScore("Mus", 4);
    	sb.addScore("Mus", 5);
    	sb.addScore("Mus", 6);
    	sb.addScore("Mus", 7);
    	sb.addScore("Pierre", 7);
    	sb.write();
    	sb.sort();
    }
}
