package core.users;
import core.food.*;
import java.util.Observer;

public interface SubscriberObserver implements Observer{
	public void updateSubscriber(Meal specialOffer);

}
