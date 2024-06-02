package core.users;
//import java.util.Observable;

import core.exceptions.SubscriberAlreadyExistsException;
import core.exceptions.SubscriberNotFoundException;

public interface SubscriberObservable {
	
	public void addSubscriber(SubscriberObserver o) throws SubscriberAlreadyExistsException;
	public void removeSubscriber(SubscriberObserver o) throws SubscriberNotFoundException;
	public void notifySubscribers();
	
}
