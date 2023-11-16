package game;

public class Mode {
	
	private static int number;
	
	private static StringBuilder type;
	
	public static void setNumber(int newNumber) {
		if (newNumber < 4 || newNumber > 5) throw new IllegalArgumentException("This mode doesn't exist, chosoe among: 4D, 4T, 5D ou 5T");
		number = newNumber;
	}
	
	public static void setType(String newType) {
		if (!newType.equals("D") && !newType.equals("T")) throw new IllegalArgumentException("This mode doesn't exist, chosoe among: 4D, 4T, 5D ou 5T");
		type = new StringBuilder(newType);
	}
	
	public static int getNumber() {
		return number;
	}
	public static StringBuilder getType() {
		return type;
	}
}
