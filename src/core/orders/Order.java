package core.orders;

import core.food.*;
import core.exceptions.ItemNotInMenuException;
import core.exceptions.ItemNotInOrderException;
import core.users.Restaurant;
import core.users.Courrier;
import core.users.Customer;

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
    private Map<MenuItem, Integer> orders;
    private boolean ordering=true;

    
    

	public Order(Restaurant restaurant,String name) {
        this.restaurant = restaurant;
        this.name= name;
        this.orders = new HashMap<>();
        this.ordering=true;
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
        orders.put(item, orders.getOrDefault(item, 0) + 1);
    }

    /**
	 * Removes an item from order
	 * @params MenuItem item
	 */
    
    public void removeItemFromOrder(MenuItem item) throws ItemNotInOrderException {
    	if (!ordering) {
            throw new IllegalStateException("Cannot remove item from order. Ordering has ended.");
        }
        if (!orders.containsKey(item)) {
            throw new ItemNotInOrderException("Item " + item.getName() + " is not in the order.");
        }
        int currentQuantity = orders.get(item);
        if (currentQuantity == 1) {
            orders.remove(item);
        } else {
            orders.put(item, currentQuantity - 1);
        }
    }
    
    /**
	 * Computes and returns the total price of the order
	 * @return double totalPrice
	 */
    
    public double getPrice() {
    	double totalPrice = 0;
        for (Map.Entry<MenuItem, Integer> entry : orders.entrySet()) {
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
		return orders;
	}
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Finds and returns a courier for the order delivery
	 * @return Courrier courier
	 */
	
	public Courrier findDeliverer() {return null;}
	
	/**
	 * Ends the order, making it unmodifiable
	 */
	public void endOrder() {
		ordering=false;
		customer.pay(this.getPrice());
	}
}
