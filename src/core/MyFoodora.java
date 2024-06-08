package core;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

import core.orders.Order;
import core.policies.DeliveryPolicy;
import core.policies.TargetProfitPolicy;
import core.users.User;

public class MyFoodora {
	
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
	
	private  static MyFoodora myFoodoraInstance;

	private MyFoodora(double markupPercentage, double deliveryCost, double serviceFee) {
		completedOrders = new ArrayList<Order>();
		users = new ArrayList<User>();
		hashedPasswords = new HashMap<Integer, Integer>();
		this.markupPercentage = markupPercentage;
		this.deliveryCost = deliveryCost;
		this.serviceFee = serviceFee;
	}

	private MyFoodora() {
		completedOrders = new ArrayList<Order>();
		users = new ArrayList<User>();
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
}
