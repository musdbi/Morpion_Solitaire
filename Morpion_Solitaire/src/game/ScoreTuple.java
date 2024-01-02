package game;

public class ScoreTuple {
	
	 private Integer score;
	 
	 private String name;

	 public ScoreTuple(Integer first, String second) {
		 if (second.isEmpty()) throw new IllegalArgumentException("Veuillez taper un nom valide.");
		 this.score = first;
		 this.name = second;
	 }

	 public Integer getScore() {
		 return score;
	 }

	 public String getName() {
	     return name;
	 }
	 
}
