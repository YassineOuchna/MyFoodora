package core.exceptions;

@SuppressWarnings("serial")
public class InvalidUserException extends Exception{
	

	/**
	 * Exception raised when an invalid User object
	 * is an input to a certain method (e.g removeUser)
	 * @param message : exception message
	 */
	public InvalidUserException(String message) {
		super(message);
	}
}
