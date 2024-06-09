package core.exceptions;

@SuppressWarnings("serial")
public class IncorrectIdentificationException extends Exception{
	/**
	 * An exception is raised when 
	 * @param message : information about invalid description
	 */
	public IncorrectIdentificationException(String message) {
		super(message);
	}

}
