package core.users;



public class Customer extends User implements Observer{
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
	
	
	public Customer(String newUsername, String password) {
		super(newUsername, password);
		
		// Default address set to center (0,0)
		this.setAddress(new double[] {0,0});

		// For better sales, notifications are on by default
		this.notificationsOn = true;
	}
	
	@Override
	public String toString() {
		return "User : Customer " + this.surname +"\n"
				+ "Username : "+ this.username + "\n"
				+ "ID : "+ super.id + "\n"
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
	public void updateSubscriber(core.food.Meal specialOffer) {
		// TODO Auto-generated method stub
		
		System.out.println("updated");
	}
	
	

	
	

}
