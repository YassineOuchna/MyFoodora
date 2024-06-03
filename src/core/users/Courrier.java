package core.users;
import core.orders.*;

public class Courrier extends User{
	private double[] position;
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


	private String phoneNumber;
	private Order[] deliveredOrders;
	private boolean onDuty;
	

	public Courrier(String newUsername, String password) {
		super(newUsername, password);
	}
	
	public boolean acceptDeliveryCall(Call call) {return true;}

}
