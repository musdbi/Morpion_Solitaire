package game;

public class ScoreTuple {
	
	 private Integer score;
	 
	 private String name;

	 public ScoreTuple(Integer first, String second) {
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
