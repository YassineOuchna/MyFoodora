package core.users;

import java.util.ArrayList;

import core.MyFoodora;
import core.exceptions.OrderNotFoundException;
import core.orders.*;

public class Courier extends User{
	public int completedDeliveries;
	private double[] position;
	private String phoneNumber;
	private ArrayList<Order> deliveredOrders;
	private boolean onDuty;
	/*
	 * A board of calling orders
	 */
	private ArrayList<Order> board;
	
	
	public Courier(String newUsername, String password) {
		super(newUsername, password);
		completedDeliveries=0;
		deliveredOrders=new ArrayList<Order>();
		board=new ArrayList<Order>();
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
	
	public int getCompletedDeliveries() {
		return completedDeliveries;
	}
	
	public ArrayList<Order> getBoard() {
		return board;
	}
	
	/**
	 * accept or refuse a delivery call for a waiting order
	 * @param decision : the decision "true" if accept, "false" if refuse
	 * @param waitingOrder : the waiting order
	 * @param myFoodora : the my Foodora system
	 */
	public void acceptDeliveryCall (boolean decision, Order waitingOrder){
		if (decision){
			waitingOrder.validateOrderByCourier();
		}else{
			//we set the state of the courier as "off-duty" to allocate another courier to the order
			this.onDuty = false;
			MyFoodora.getInstance().getDeliveryPolicy().assignCourrier(waitingOrder);
		}
		this.board.remove(waitingOrder);
		
	}
	public void increaseCounter() {
		this.completedDeliveries++;
	}
	
	public Order findOrderIdInBoard(int id) throws OrderNotFoundException {
		for (Order o : this.board) {
	        if (o.getId() == id) {
	            return o;
	        }
	    }
	    throw new OrderNotFoundException("Order with ID " + id + " not found.");
	}
}
