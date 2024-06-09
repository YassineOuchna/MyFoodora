package core.users;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import core.orders.Order;
import core.policies.*;
import core.MyFoodora;
import core.comparators.*;
import core.food.MenuItem;

public class Manager extends User{
	
	
	// Unique app instance
	private MyFoodora app = MyFoodora.getInstance();
	
	// Shipped order sorting policy
	private OrderSortingPolicy orderSortingPolicy;

	public Manager(String newUsername, String password) {
		super(newUsername, password);
	}
	public Manager(String username, String password,String name, String surname) {
		super(username, password);
		super.setName(name);
		super.setSurname(surname);
	}
	
	
	
	public void addUser(User u) {
		app.addUser(u);
	}
	public void removeUser(User u) {
		app.removeUser(u);
	}

	public void activateUser(User u) {
		u.setActive(true);
	}
	public void desactivateUser(User u) {
		u.setActive(false);
	}
	public void setServiceFee(double fee) {
		app.setServiceFee(fee);
	}
	public double totalProfit(Date start,Date end) {
		return app.computeTotalProfit(start, end);
	}
	public double totalIncome(Date start, Date end) {
		return app.computeTotalIncome(start, end);
	}
	
	/**
	 * Sorts all the ordered menu items according
	 * to the current orderSortingPolicy
	 * @return a list of menu items sorted according 
	 * to the order sorting policy 
	 */
	public ArrayList<MenuItem> shippedItemSort(){
		return orderSortingPolicy.sort();
	}
	
	/**
	 * Computes the average income by customer
	 * in a time period
	 * @param start :  start of the time period
	 * @param end : end of the time period 
	 * @return the average income by customer
	 */
	public double averageIncomeByCustomer(Date start, Date end) {
		// total income
		double totalIncome = totalIncome(start, end);
		// Finding the number of customers 
		HashSet<Integer> realCustomers = new HashSet<Integer>();
		for (Order o : app.getCompletedOrders()) {
			Customer c = o.getCustomer();
			if (o.getDate().after(start) && o.getDate().before(end)) {
				if (!realCustomers.contains(c.getId())) {
					realCustomers.add(c.getId());
				}
			}
		}
		return totalIncome / realCustomers.size();
	}
	
	
	
	/**
	 * Computes the varying profit related parameter according to 
	 * the app's target profit policy and changes it to the new value.
	 * @param targetProfit : target profit policy of the MyFooora app.
	 */
	public void meetTargetProfit(double targetProfit) {
		TargetProfitPolicy profitPolicy = app.getProfitPolicy();
        if (profitPolicy == null) {
            throw new IllegalStateException("No target profit policy set.");
        }
        // computes the parameter and sets it up
        profitPolicy.meetTargetProfit(targetProfit);
    }

	public void setDeliveryPolicy(DeliveryPolicy deliveryPolicy) {
		app.setDeliveryPolicy(deliveryPolicy);
	}
	
	public void setTargetProfitPolicy(TargetProfitPolicy profitPolicy) {
		app.setProfitPolicy(profitPolicy);
	}


	/*
	 * Obtaining all delivered orders
	 */
    public ArrayList<Order> getAllDeliveredOrders() {
        return Order.getAllDeliveredOrders();
    }
    /*
     * Obtaining delivered Orders between Date date1 and Date date2
     */
    public ArrayList<Order> getDeliveredOrders(Date date1, Date date2) {
    	ArrayList<Order> deliveredOrders = new ArrayList<Order>();
    	for (Order o : Order.getAllDeliveredOrders()) {
    		if (o.getDate().after(date1) && o.getDate().before(date2)) {
    			deliveredOrders.add(o);
    		}
    	}
        return deliveredOrders;
    }
    /**
     * Gets a last month's orders on the MyFoodora app
     * @return ArrayList<Order> containing last month's orders
     */
    public ArrayList<Order> getLastMonthOrders() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);  
        Date lastMonth = cal.getTime();
        return getDeliveredOrders(lastMonth,new Date());  
    }
    
    /**
     * Sorts restaurants with the most delivered orders 
     * @return an ordered ArrayList of restaurants in descending order
     */
    public ArrayList<Restaurant> showRestaurantTop(){
    	ArrayList<Restaurant> allRestaurants = app.getRestaurants();
        allRestaurants.sort(new RestaurantComparator());
        Collections.reverse(allRestaurants);  // Sort in descending order
        return allRestaurants;
    }
    
 
	/**
	 * determining the restaurant that sold the most
	 * @return the most selling restaurant
	 */
	public Restaurant mostSellingRestaurant() {
		return app.mostSellingRestaurant();
	}
	
	/**
	 * determining the restaurant that sold the least
	 * @return the least selling restaurant
	 */
	public Restaurant leastSellingRestaurant() {
		return app.leastSellingRestaurant();
	}
	
	/**
	 * determining the courier that delivered the most orders
	 * @return the most active courier
	 */
	public Courier mostActiveCourier() {
		return app.mostActiveCourier();
	}
	
	/**
	 * determining the courier that delivered the fewest orders
	 * @return the least active courier
	 */
	public Courier leastActiveCourier() {
		return app.leastActiveCourier();
	}

	
	/**
	 * change the markup percentage of the system
	 * @param percentage the new markup percentage of the system
	 */
	public void setMarkupPercentage(double percentage) {
		app.setMarkupPercentage(percentage);
	}
	
	/**
	 * change the delivery cost of the system
	 * @param delivery the new delivery cost of the system
	 */
	public void setDeliveryCost(double delivery) {
		app.setDeliveryCost(delivery);
	}
	
	
	public OrderSortingPolicy getOrderSortingPolicy() {
		return orderSortingPolicy;
	}
	public void setOrderSortingPolicy(OrderSortingPolicy orderSortingPolicy) {
		this.orderSortingPolicy = orderSortingPolicy;
	}
	
	@Override
	public String getUserType() {return "manager";}
    
}
