package core.users;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import core.orders.Order;
import core.policies.*;
import core.comparators.*;

public class Manager extends User{
	
	/*
	 * The manager is the one who sets the policy (i.e strategy) for the delivery and the profit
	 */
	private DeliveryPolicy deliveryPolicy;
	private TargetProfitPolicy profitPolicy;

	

	public Manager(String newUsername, String password) {
		super(newUsername, password);
	}
	
	public Manager(String newUsername, String password, TargetProfitPolicy targetProfitPolicy) {
        super(newUsername, password);
        this.setProfitPolicy(targetProfitPolicy);
    }
	
	public Manager(String newUsername, String password, TargetProfitPolicy targetProfitPolicy, DeliveryPolicy deliveryPolicy) {
        super(newUsername, password);
        this.setProfitPolicy(targetProfitPolicy);
        this.deliveryPolicy=deliveryPolicy;
    }
	
	public DeliveryPolicy getDeliveryPolicy() {
		return deliveryPolicy;
	}

	public void setDeliveryPolicy(DeliveryPolicy deliveryPolicy) {
		this.deliveryPolicy = deliveryPolicy;
	}
	public void addUser(User u) {}
	public void removeUser(User u) {}
	public void activateUser(User u) {}
	public void desactivateUser(User u) {}
	public void setServiceFee(double fee) {}
	public double totalProfit(Date start,Date end) {return 0;}
	public double totalIncome(Date start, Date end) {return 0;}
	public double averageIncomeByCustomer(Date start, Date end) {return 0;}
	
	
	
	
	
	public TargetProfitPolicy getProfitPolicy() {
		return profitPolicy;
	}
	public void setProfitPolicy(TargetProfitPolicy profitPolicy) {
		this.profitPolicy = profitPolicy;
	}
	
	/*
	 * Computing profit parameters
	 */
	public double computeDeliveryCost(double targetProfit, int totalIncome, double serviceFee, double markup) {
        if (profitPolicy == null) {
            throw new IllegalStateException("No target profit policy set.");
        }
        return profitPolicy.computeDeliveryCost(targetProfit, totalIncome, serviceFee, markup);
    }

    public double computeServiceFee(double targetProfit, int totalIncome, double deliveryCost, double markup) {
        if (profitPolicy == null) {
            throw new IllegalStateException("No target profit policy set.");
        }
        return profitPolicy.computeServiceFee(targetProfit, totalIncome, deliveryCost, markup);
    }

    public double computeMarkup(double targetProfit, int totalIncome, double deliveryCost, double serviceFee) {
        if (profitPolicy == null) {
            throw new IllegalStateException("No target profit policy set.");
        }
        return profitPolicy.computeMarkup(targetProfit, totalIncome, deliveryCost, serviceFee);
    }

	/*
	 * Obtaining all delivered orders
	 */
    public Map<Date, Order> getAllDeliveredOrders() {
        return Order.getAllDeliveredOrders();
    }
    /*
     * Obtaining delivered Orders between Date date1 and Date date2
     */
    public Map<Date, Order> getDeliveredOrders(Date date1, Date date2) {
        Map<Date, Order> deliveredOrders = new HashMap<>();
        for (Map.Entry<Date, Order> entry : Order.getAllDeliveredOrders().entrySet()) {
            if (!entry.getKey().before(date1) && !entry.getKey().after(date2)) {
                deliveredOrders.put(entry.getKey(), entry.getValue());
            }
        }
        return deliveredOrders;
    }
	/*
	 * Obtaining all delivered orders in the last month
	 */
    public Map<Date, Order> getLastMonthOrders() {
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
