package core.exceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception{
	public UserNotFoundException() {
	}

	/**
	 * @param message : message given when the user who wants to login isNotFound in the users of the system
	 */
	public UserNotFoundException(String message) {
		super();
	}

}
