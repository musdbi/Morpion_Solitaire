package game;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

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
    		writer.write("Tableau des scores :");
    		writer.newLine();
    		writer.newLine();
    		writer.write("--------------------------------------------------------");
    		writer.newLine();
    		writer.newLine();
    	    for (Entry<Integer, String> entry : scores.entrySet()) {
    	        writer.write(entry.getKey() + " points joués - " + entry.getValue());
    	        writer.newLine();
    	        writer.newLine();
    	    }
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    }
    
    public void append() {
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(path,true))) {
    		writer.write("--------------------------------------------------------");
    		writer.newLine();
    		writer.newLine();
    	    for (Entry<Integer, String> entry : scores.entrySet()) {
    	        writer.write(entry.getKey() + " points joués - " + entry.getValue());
    	        writer.newLine();
    	        writer.newLine();
    	    }
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
    	sb.write();
    	sb.append();
    }
}
