package core.orders;

import core.food.*;
import core.MyFoodora;
import core.exceptions.ItemNotInMenuException;
import core.exceptions.ItemNotInOrderException;
import core.users.Restaurant;
import core.users.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {
	private Customer customer;
	/*
	 * An Order has a name and belongs to a 
	 * restaurant.
	 */
	private String name;
	private Restaurant restaurant;
	/*
	 * An Order normally is a Map of dishes/meals
	 * equipped with the quantity of each ordered
	 * menu item.
	 */
    private Map<MenuItem, Integer> orderItems;
    /*
     * An order can be expanded as long as it has not ended yet, 
     * ordering follows the state of the order 
     */
    private boolean ordering=true;
    
    /*
     * An order is equipped with a Date
     */
    private Date date;
    
    /*
     * We need to store all delivered Orders
     */
    private static ArrayList<Order> deliveredOrders = new ArrayList<Order>();

    
    

	public Order(Restaurant restaurant,String name) {
        this.restaurant = restaurant;
        this.name= name;
        this.orderItems = new HashMap<>();
        this.ordering=true;
        this.date= new Date();
    }
	public Order(Restaurant restaurant,String name, Customer customer) {
        this.restaurant = restaurant;
        this.name= name;
        this.orderItems = new HashMap<>();
        this.ordering=true;
        this.customer=customer;
        this.date= new Date();
    }
	
	/**
	 * Adds an item to order
	 * @params MenuItem item
	 */

    public void addItem2Order(MenuItem item) throws ItemNotInMenuException {
    	if (!ordering) {
            throw new IllegalStateException("Cannot add item to order. Ordering has ended.");
        }
        if (!restaurant.getMenu().getItems().containsKey(item.getName())) {
            throw new ItemNotInMenuException("Item " + item.getName() + " is not available in the restaurant's menu.");
        }
        orderItems.put(item, orderItems.getOrDefault(item, 0) + 1);
    }

    /**
	 * Removes an item from order
	 * @params MenuItem item
	 */
    
    public void removeItemFromOrder(MenuItem item) throws ItemNotInOrderException {
    	if (!ordering) {
            throw new IllegalStateException("Cannot remove item from order. Ordering has ended.");
        }
        if (!orderItems.containsKey(item)) {
            throw new ItemNotInOrderException("Item " + item.getName() + " is not in the order.");
        }
        int currentQuantity = orderItems.get(item);
        if (currentQuantity == 1) {
            orderItems.remove(item);
        } else {
            orderItems.put(item, currentQuantity - 1);
        }
    }
    
    /**
	 * Computes and returns the total price of the order
	 * @return double totalPrice
	 */
    
    public double getPrice() {
    	double totalPrice = 0;
        for (Map.Entry<MenuItem, Integer> entry : orderItems.entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        return totalPrice;
    }
    
    public Restaurant getRestaurant() {
        return restaurant;
    }
    public Restaurant setRestaurant() {
        return restaurant;
    }

    public Map<MenuItem, Integer> getOrders() {
		return orderItems;
	}
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public static ArrayList<Order> getAllDeliveredOrders() {
        return deliveredOrders;
    }
	public Customer getCustomer() {
		return customer;
	}
	
	
	/**
	 * Ends the order, making it unmodifiable
	 */
	public void endOrder() {
		ordering=false;
		customer.pay(this.getPrice());
		customer.getFidelityCard().updateCard(this);
		for (MenuItem item : orderItems.keySet()) {
			item.orderFrequency=item.orderFrequency+orderItems.get(item);
		}
		MyFoodora app = MyFoodora.getInstance();
		app.findDeliverer(this);
		deliveredOrders.add(this);
		restaurant.addDeliveredOrder();
	}
	public Date getDate() {
		return date;
	}

	
}
