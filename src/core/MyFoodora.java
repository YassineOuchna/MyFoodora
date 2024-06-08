package core;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

import core.exceptions.SubscriberAlreadyExistsException;
import core.exceptions.SubscriberNotFoundException;
import core.food.Meal;
import core.orders.Order;
import core.policies.DeliveryPolicy;
import core.policies.TargetProfitPolicy;
import core.users.Courrier;
import core.users.Customer;
import core.users.Restaurant;
import core.users.SubscriberObservable;
import core.users.SubscriberObserver;
import core.users.User;

public class MyFoodora implements SubscriberObservable{
	
	// Array of global users of MyFoodoraApp
	private ArrayList<User> users;

	// HashMap of user IDs and their hashed passwords
	private HashMap<Integer, Integer> hashedPasswords;
	
	// List of all the global orders on the app
	private ArrayList<Order> completedOrders;
	
	// Policies 
	private  DeliveryPolicy deliveryPolicy;
	private  TargetProfitPolicy profitPolicy;

	// Fees and mark-up percentage
	private double markupPercentage;
	private double deliveryCost;
	private double serviceFee;
	
	// Notifying customers of new special Offers
	private ArrayList<SubscriberObserver> subscribedCustomers;
	
	// Unique instance of the app
	private  static MyFoodora myFoodoraInstance;

	private MyFoodora(double markupPercentage, double deliveryCost, double serviceFee) {
		completedOrders = new ArrayList<Order>();
		users = new ArrayList<User>();
		hashedPasswords = new HashMap<Integer, Integer>();
		this.subscribedCustomers = new ArrayList<SubscriberObserver>();
		this.markupPercentage = markupPercentage;
		this.deliveryCost = deliveryCost;
		this.serviceFee = serviceFee;
	}

	private MyFoodora() {
		completedOrders = new ArrayList<Order>();
		users = new ArrayList<User>();
		this.subscribedCustomers = new ArrayList<SubscriberObserver>();
		hashedPasswords = new HashMap<Integer, Integer>();
	}
	
	public static MyFoodora getInstance() {
		if (myFoodoraInstance == null) {
			myFoodoraInstance = new MyFoodora();
		}
		return myFoodoraInstance;
	}
	
	public void addUser(User u) {
		users.add(u);
		hashedPasswords.put(u.getId(), u.getHashedPassword());
	}
	
	public void removeUser(User u) {
		for (User usr: users) {
			if (usr.getId() == u.getId()) {
				users.remove(u);
				hashedPasswords.remove(u.getId());
			}
		}
	}

	
	public double computeTotalIncome() {
		double total = 0;
		for (Order o : completedOrders) {
			total = total + o.getPrice();
		}
		return total;
	}
	public double computeTotalIncome(Date date1, Date date2) {
		double total = 0;
		for (Order o : completedOrders) {
    		if (o.getDate().after(date1) && o.getDate().before(date2)) {
			total = total + o.getPrice();
    		}
		}
		return total;
	}
	public double computeTotalProfit(Date date1, Date date2) {
		double total = 0;
		for (Order o : completedOrders) {
    		if (o.getDate().after(date1) && o.getDate().before(date2)) {
    			total = total + o.getPrice()*markupPercentage + serviceFee - deliveryCost;
    		}
		}
		return total;
	}
	public double computeTotalProfit() {
		double total = 0;
		for (Order o : completedOrders) {
			total = total + o.getPrice()*markupPercentage + serviceFee - deliveryCost;
		}
		return total;
	}
	
	public double getMarkupPercentage() {
		return markupPercentage;
	}
	public void setMarkupPercentage(double markupPercentage) {
		this.markupPercentage = markupPercentage;
	}
	public double getDeliveryCost() {
		return deliveryCost;
	}
	public void setDeliveryCost(double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}
	public double getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}
	public ArrayList<User> getUsers() {
		return users;
	}
	public HashMap<Integer, Integer> getHashedPasswords() {
		return hashedPasswords;
	}
	public ArrayList<Order> getCompletedOrders() {
		return completedOrders;
	}
	
	public  DeliveryPolicy getDeliveryPolicy() {
		return deliveryPolicy;
	}
	public  void setDeliveryPolicy(DeliveryPolicy deliveryPolicy) {
		this.deliveryPolicy = deliveryPolicy;
	}
	public  TargetProfitPolicy getProfitPolicy() {
		return profitPolicy;
	}
	public  void setProfitPolicy(TargetProfitPolicy profitPolicy) {
		this.profitPolicy = profitPolicy;
	}
	
	public ArrayList<SubscriberObserver> getSubscribedCustomers(){
		return subscribedCustomers;
	}

	@Override
	public void addSubscriber(SubscriberObserver o) throws SubscriberAlreadyExistsException {
            if (subscribedCustomers.contains(o)) {
                throw new core.exceptions.SubscriberAlreadyExistsException("Subscriber already exists " + o);}
            
            subscribedCustomers.add(o);
      
	}


	@Override
	public void removeSubscriber(SubscriberObserver o) throws SubscriberNotFoundException {
            if (!subscribedCustomers.contains(o)) {
                throw new core.exceptions.SubscriberNotFoundException("Subscriber doesn't exist : " + o);
            }
            subscribedCustomers.remove(o);
	}


	@Override
	public void notifySubscribers(String restaurantName, Meal specialOffer) {
		for (SubscriberObserver ob: subscribedCustomers) {
				ob.updateSubscriber(restaurantName, specialOffer);
		}
	}
	
	
	

    public User login(String username, String password) throws Exception {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getHashedPassword()==password.hashCode()) {
                return user;
            }
        }
        throw new Exception("Login failed");
    }

    // Other necessary methods

    public void setUserLastID(int lastID) {
        this.userLastID = lastID;
    }

    public int getUserLastID() {
        return this.userLastID;
    }

    public List<User> getUsers() {
        return users;
    }
}
