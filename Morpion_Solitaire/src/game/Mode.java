package game;

public class Mode {
	
	private static int number = 5;
	
	private static StringBuilder type = new StringBuilder("T");
	
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
	
	public static String toStringStatic () {
        if (Mode.getNumber() == 4 && Mode.getType().toString().equals("T")) return "4T";
    	if (Mode.getNumber() == 4 && Mode.getType().toString().equals("D")) return "4D";
    	if (Mode.getNumber() == 5 && Mode.getType().toString().equals("T")) return "5T";
    	return "5D";
    }
}
