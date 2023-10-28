package game;

import java.util.InputMismatchException;

public class ScoreTuple {
	
	 private Integer score;
	 
	 private String name;

	 public ScoreTuple(Integer first, String second) {
		 this.score = first;
		 if (second.isEmpty()) throw new InputMismatchException("Veuillez taper un nom valide.");
		 this.name = second;
	 }

	 public Integer getScore() {
		 return score;
	 }

	 public String getName() {
	     return name;
	 }
	 
}
