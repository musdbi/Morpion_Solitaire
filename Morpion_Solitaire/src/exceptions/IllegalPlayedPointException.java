package exceptions;

public class IllegalPlayedPointException extends Exception {
	public IllegalPlayedPointException (String message) {
	       super(message) ;
	}
	
	public IllegalPlayedPointException () {
	       super() ;
	}
}
