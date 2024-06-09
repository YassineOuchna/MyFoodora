package core.users;

import java.util.ArrayList;

import core.orders.*;

public class Courier extends User{
	public int completedDeliveries;
	private double[] position;
	private String phoneNumber;
	private ArrayList<Order> deliveredOrders;
	private boolean onDuty;
	
	public Courier(String newUsername, String password) {
		super(newUsername, password);
	}
	public Courier(String newUsername,String password, double[] position,boolean onDuty,int completedDeliveries) {
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

	public ArrayList<Order> getDeliveredOrders() {
		return deliveredOrders;
	}

	public boolean acceptDeliveryCall(Order order) {return true;}
	
	public int getCompletedDeliveries() {
		return completedDeliveries;
	}
	
}
