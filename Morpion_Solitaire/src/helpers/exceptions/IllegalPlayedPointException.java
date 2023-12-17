package helpers.exceptions;

public class IllegalPlayedPointException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalPlayedPointException (String message) {
	       super(message) ;
	}
	
	public IllegalPlayedPointException () {
	       super() ;
	}
}
