package core.exceptions;

@SuppressWarnings("serial")
public class SubscriberNotFoundException extends Exception{
	public SubscriberNotFoundException(String message) {
        super(message);
    }
}
