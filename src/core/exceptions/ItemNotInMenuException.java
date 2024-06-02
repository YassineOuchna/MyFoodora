package core.exceptions;

@SuppressWarnings("serial")
public class ItemNotInMenuException extends Exception{

	/**
	 * An exception raised when trying to remove
	 * a non-existing item of a menu
	 */
	public ItemNotInMenuException(String message) {
		super(message);
	}

}
