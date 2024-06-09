package core.users;
import java.util.ArrayList;

import core.MyFoodora;
import core.fidelityCards.BasicFidelityCard;
import core.fidelityCards.FidelityCard;
import core.fidelityCards.LotteryFidelityCard;
import core.fidelityCards.PointFidelityCard;
import core.orders.Order;



public class Customer extends User implements SubscriberObserver{
	
	// Customer's address is stored as two real values
	private double[] address;
	
	/*
	 * Storing whether or not the customer
	 * wants to be notified of special offers
	 * from restaurants 
	 */
	
	private boolean notificationsOn;
	
	/*
	 * Storing the list of Orders of the customer
	 * and their Fidelity plan
	 */
	
	//private ArrayList<Order> orderHistory;
	
	private FidelityCard fidelityCard;
	
	/*
	 * A customer 
	 */
	private ArrayList<String> receivedEmails;
		
	
	public Customer(String newUsername, String password, String name, String surname) {
		super(newUsername, password);
		super.setName(name);
		super.setSurname(surname);
		
		// Default address set to origin (0,0)
		address = new double[] {0,0};

		// By default, notifications are off
		this.notificationsOn = false;
		this.receivedEmails= new ArrayList<String>();
		
		// By default, basic fidelity plan 
		fidelityCard = new BasicFidelityCard();
	}
	

	public Customer(String newUsername, String password) {
		super(newUsername, password);
		
		// Default address set to origin (0,0)
		address = new double[] {0,0};

		// By default, notifications are off
		this.notificationsOn = false;
		this.receivedEmails= new ArrayList<String>();
		
		// By default, basic fidelity plan 
		fidelityCard = new BasicFidelityCard();
	}
	
	@Override
	public String toString() {
		return "User : Customer " + this.getSurname() +"\n"
				+ "Username : "+ this.getUsername()+ "\n"
				+ "ID : "+ super.getId() + "\n"
				+ "Address : "+ this.address + "\n"
				+ "Fidelity plan : " + this.fidelityCard + "\n"
				+"Special offers notifications : " + (isNotificationsOn() ? "On" : "Off") + "\n";
	}


	public boolean isNotificationsOn() {
		return notificationsOn;
	}


	public void setNotificationsOn(boolean notificationsOn) {
		this.notificationsOn = notificationsOn;
	}


	public double[] getAddress() {
		return address;
	}


	public void setAddress(double[] address) {
		this.address = address;
	}

	@Override
	public void updateSubscriber(String restaurantName,core.food.Meal specialOffer) {
		if (this.notificationsOn) {
			this.receiveMail("New Special Offer available from restaurant"+ restaurantName+" : "+ specialOffer.toString());	
		}
	}
	/**
	 * Registers the customer to the specified fidelity card.
	 * If the same fidelity plan is specified again, the customer 
	 * keeps his old card with its advantages.
	 * @param fidelityCard : a fidelity card.
	 */
	public void registerFidelityCard(FidelityCard fidelityCard) {
		if (!(this.fidelityCard.getClass() == fidelityCard.getClass())) {
			this.fidelityCard = fidelityCard;
		}
	}
	public void registerFidelityCard(String fidelityCardType) {
		if (fidelityCardType.equals("basic")) {
			this.fidelityCard = new BasicFidelityCard();
		}
		else if (fidelityCardType.equals("point")) {
			this.fidelityCard = new PointFidelityCard();
		}
		else if (fidelityCardType.equals("lottery")) {
			this.fidelityCard = new LotteryFidelityCard();
		}
	}
	
	/**
	 * Unregisters the customer from old fidelity plan.
	 * The new plan is the default one given upon registration.
	 */
	public void unregisterFidelityCard() {
		this.fidelityCard = new BasicFidelityCard();
	}
	public void receiveMail(String message) {
		receivedEmails.add(message);
	}

	public ArrayList<String> getReceivedEmails() {
		return receivedEmails;
	}

	
	
	public void pay(double price) {}

	public FidelityCard getFidelityCard() {
		return fidelityCard;
	}
	@Override
	public String getUserType() {return "customer";}
	
	/**
	 * displays the informations of the user's fidelity card plan
	 */
	public void displayFidelityInfo(){
		System.out.println(this.fidelityCard.toString());
	}
	
	/**
	 * gets the history of all the orders the customer made on MyFoodora
	 * @param myFoodora : the MyFoodora core
	 * @return historyOfOrders : all the orders the customer made on MyFoodora
	 */
	public ArrayList<Order> getHistoryOfOrders (MyFoodora myFoodora){
		ArrayList<Order> historyOfOrders = new ArrayList<Order>();
		
		ArrayList<Order> completedOrders = myFoodora.getCompletedOrders();
		for(Order order : completedOrders){
			//we seek the completed orders made by the customer
			if (order.getCustomer().getId() == this.getId()){
				historyOfOrders.add(order);
			}
		}
		return historyOfOrders;
	}


}
