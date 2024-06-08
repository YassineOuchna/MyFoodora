package core.users;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import core.orders.Order;
import core.policies.*;
import core.MyFoodora;
import core.comparators.*;

public class Manager extends User{
	
	
	private MyFoodora app = MyFoodora.getInstance();

	public Manager(String newUsername, String password) {
		super(newUsername, password);
	}
	public Manager(String username, String password,String name, String surname) {
		super(username, password);
		super.setName(name);
		super.setSurname(surname);
	}
	
	
	public void addUser(User u) {
		app.getUsers().add(u);
	}
	public void removeUser(User u) {}

	public void activateUser(User u) {}
	public void desactivateUser(User u) {}
	public void setServiceFee(double fee) {}
	public double totalProfit(Date start,Date end) {return 0;}
	public double totalIncome(Date start, Date end) {return 0;}
	public double averageIncomeByCustomer(Date start, Date end) {return 0;}
	
	
	
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
    public ArrayList<Restaurant> showRestaurantTop(){
    	ArrayList<Restaurant> allRestaurants = Restaurant.getAllRestaurants();
        allRestaurants.sort(new RestaurantComparator());
        Collections.reverse(allRestaurants);  // Sort in descending order
        return allRestaurants;
    }
}
