package core.users;
import java.util.Observable;


public interface SubscriberObservable extends Observable{
	
	public void addSubscriber(SubscriberObserver o);
	public void removeSubscriber(SubscriberObserver o);
	public void notifySubsriber();
}
