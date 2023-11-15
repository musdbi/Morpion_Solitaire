package game;

public class Mode {
	
	private int number;
	
	private String type;
	
	public Mode(int number, String type) throws IllegalArgumentException{
		if (number < 4 || number > 5 || (!type.equals("D") && !type.equals("T"))) throw new IllegalArgumentException("Ce mode de jeux n'existe pas: sélectionner 4D, 4T, 5D ou 5T");
		this.number = number;
		this.type = type;
	}
	
	public int getNumber() {
		return this.number;
	}
	public String getType() {
		return this.type;
	}
}
