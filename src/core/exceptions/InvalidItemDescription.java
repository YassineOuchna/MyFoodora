package core.exceptions;

@SuppressWarnings("serial")
public class InvalidItemDescription extends Exception{

	/**
	 * An exception is raised when creating
	 * a menu item with invalid description.
	 * @param message : information about invalid description
	 */
	public InvalidItemDescription(String message) {
		super(message);
	}
}
