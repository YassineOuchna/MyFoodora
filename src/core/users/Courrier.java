package core.users;
import core.orders.*;

public class Courrier extends User{
	public int completedDeliveries;
	private double[] position;
	private String phoneNumber;
	private Order[] deliveredOrders;
	private boolean onDuty;
	/*
	 * comment
	 */
	
	public Courrier(String newUsername, String password) {
		super(newUsername, password);
	}
	public Courrier(String newUsername,String password, double[] position,boolean onDuty,int completedDeliveries) {
		super(newUsername, password);
		this.position=position;
		this.onDuty=onDuty;
		this.completedDeliveries=completedDeliveries;
	}
	

	public double[] getPosition() {
		return position;
	}
	public void setPosition(double[] position) {
		this.position = position;
	}
	public boolean isOnDuty() {
		return onDuty;
	}
	public void setOnDuty(boolean onDuty) {
		this.onDuty = onDuty;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Order[] getDeliveredOrders() {
		return deliveredOrders;
	}


	
	
	public boolean acceptDeliveryCall(Call call) {return true;}
	
	public int getCompletedDeliveries() {
		return completedDeliveries;
	}

	

}
