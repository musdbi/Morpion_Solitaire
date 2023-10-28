package helpers;

public class OutOfGridException extends IllegalArgumentException {
	public OutOfGridException (String message) {
	       super(message) ;
	}
	
	public OutOfGridException () {
		super();
	}
}
