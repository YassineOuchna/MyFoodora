package core.exceptions;

@SuppressWarnings("serial")
public class FoodItemNotFoundException extends Exception{
	
	public FoodItemNotFoundException(String message) {
		super(message);
	}
}
