package core.exceptions;

@SuppressWarnings("serial")
public class SubscriberAlreadyExistsException extends Exception{
	public SubscriberAlreadyExistsException(String message) {
        super(message);
    }
}
