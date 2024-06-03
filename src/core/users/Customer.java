package core.users;
import java.util.ArrayList;



public class Customer extends User implements SubscriberObserver{
	private String phoneNumber;
	
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
	
	//private FidelityPlan fidelityPlan;
	
	/*
	 * A customer 
	 */
	private ArrayList<String> receivedEmails;
	
	
	private String email;
	
	
	public Customer(String newUsername, String password) {
		super(newUsername, password);
		
		// Default address set to center (0,0)
		this.setAddress(new double[] {0,0});

		// By default, notifications are off
		this.notificationsOn = false;
		this.receivedEmails= new ArrayList<String>();
	}
	
	@Override
	public String toString() {
		return "User : Customer " + this.getSurname() +"\n"
				+ "Username : "+ this.getUsername()+ "\n"
				+ "ID : "+ super.getId() + "\n"
				+ "Address : "+ this.address + "\n"
				+"Special offers notifications : " + (isNotificationsOn() ? "On" : "Off") + "\n";
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	public void receiveMail(String message) {
		receivedEmails.add(message);
	}

	public ArrayList<String> getReceivedEmails() {
		return receivedEmails;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void pay(double price) {}

}
