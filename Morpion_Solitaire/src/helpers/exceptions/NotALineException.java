package helpers.exceptions;

public class NotALineException extends IllegalArgumentException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotALineException (String message) {
	       super(message) ;
	}
	
	public NotALineException () {
		super("Not a line, give a valid number");
	}
}
