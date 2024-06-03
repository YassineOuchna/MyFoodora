package core.exceptions;

@SuppressWarnings("serial")
public class ItemNotInOrderException extends Exception {
    public ItemNotInOrderException(String message) {
        super(message);
    }
}
