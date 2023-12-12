package helpers.exceptions;

public class NotALineException extends IllegalArgumentException {
	public NotALineException (String message) {
	       super(message) ;
	}
	
	public NotALineException () {
		super("Not a line, give a valid number");
	}
}
