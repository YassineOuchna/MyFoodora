package core.users;

import java.util.ArrayList;
import java.util.Arrays;

import core.orders.*;

public class Courier extends User{
	public int completedDeliveries;
	private double[] position;
	private String phoneNumber;
	private ArrayList<Order> deliveredOrders;
	private boolean onDuty;
	
	
	public Courier(String newUsername, String password) {
		super(newUsername, password);
		completedDeliveries=0;
		// By default, the courier isn't on duty
		onDuty = false;
		deliveredOrders=new ArrayList<Order>();
	}
	public Courier(String newUsername,String password, double[] position,boolean onDuty,int completedDeliveries) {
		super(newUsername, password);
		this.position=position;
		this.onDuty=onDuty;
		this.completedDeliveries=completedDeliveries;
	}
	
	

	@Override
	public String toString() {
		return "Courier [completedDeliveries=" + completedDeliveries + ", position=" + position[0] + "," + position[1] 
				+ ", phoneNumber=" + phoneNumber + ", deliveredOrders=" + deliveredOrders + ", onDuty=" + onDuty + "]";
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
	
	public int getCompletedDeliveries() {
		return completedDeliveries;
	}
	
	
	public void increaseCounter() {
		this.completedDeliveries++;
	}
	
}
