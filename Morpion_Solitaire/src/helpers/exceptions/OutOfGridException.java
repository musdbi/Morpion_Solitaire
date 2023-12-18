package helpers.exceptions;

public class OutOfGridException extends IllegalArgumentException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OutOfGridException (String message) {
	       super(message) ;
	}
	
	public OutOfGridException () {
		super();
	}
}
