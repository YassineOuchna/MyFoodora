package core.users;


import java.util.ArrayList;

import core.MyFoodora;
import core.exceptions.InvalidItemDescription;
import core.exceptions.ItemNotInMenuException;
import core.food.*;
import core.policies.ItemSort;
import core.policies.OrderSortingPolicy;

public class Restaurant extends User {
	private double[] location;
	private Menu menu;
	private double genericDiscount;
	private double specialDiscount;
	private OrderSortingPolicy orderSortingPolicy;
	
	/*
	 * Storing the number of delivered orders from the restaurant
	 */
	private int numDeliveredOrders;
	
	
	
	public Restaurant(String newUsername, String password) {
		super(newUsername, password);
		this.location = new double[] {0,0};
		
		// Initialize empty menu
		this.menu = new Menu(this);
		
		// Default discount values
		this.genericDiscount = 0.05;
		this.specialDiscount= 0.1;
		this.numDeliveredOrders=0;

		// By default the orderSortingPolicy 
		// is the item sort
		this.orderSortingPolicy = new ItemSort();
	}

	public Restaurant(String newUsername, String password, double[] location) {
		super(newUsername, password);
		this.location = location;
		
		// Initialize empty menu
		this.menu = new Menu(this);
		
		// Default discount values
		this.genericDiscount = 0.05;
		this.specialDiscount= 0.1;
		this.numDeliveredOrders=0;
		// By default the orderSortingPolicy 
		// is the item sort
		this.orderSortingPolicy = new ItemSort();
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Restaurant Details:\n");
	    sb.append("Username: ").append(this.getName()).append("\n");
	    sb.append("Location: [").append(this.location[0]).append(", ").append(this.location[1]).append("]\n");
	    sb.append("Generic Discount: ").append(this.genericDiscount).append("\n");
	    sb.append("Special Discount: ").append(this.specialDiscount).append("\n");
	    sb.append("Number of Delivered Orders: ").append(this.numDeliveredOrders).append("\n");
	    sb.append("Order Sorting Policy: ").append(this.orderSortingPolicy.getClass().getSimpleName()).append("\n");
	    sb.append("Menu:\n");
	    sb.append(this.menu.getItems()).append("\n"); // Assuming `getItems()` returns a readable format of menu items
	    return sb.toString();
	}
	
	/**
	 * Sorts all the ordered menu items according
	 * to the current orderSortingPolicy
	 * @return a list of menu items sorted according 
	 * to the order sorting policy 
	 */
	public ArrayList<MenuItem> shippedItemSort(){
		return orderSortingPolicy.sort(this.getMenu().getItems().values());
	}

	/**
	 * This method adds an item
	 * to the restaurant's menu 
	 * @param itemType : type of the item
	 * @param description : specific array of strings to describe
	 * the item. Order is important as well as certain values.
	 * The first element must always indicate a unique name for the item.
	 * @throws InvalidItemDescription : if the description array holds
	 * invalid values.
	 */
	public void addMenuItem(String itemType, String[] description) throws InvalidItemDescription{
		this.menu.addItem(itemType, description);
	}

	
	/**
	 * Removes the item from the menu
	 * @param itemName : name of the item to remove
	 * @throws an ItemNotInMenuException if the item isn't in the menu
	 */
	public void removeItem(String itemName) throws ItemNotInMenuException {
		this.menu.removeItem(itemName);
	}
	
	
	public double[] getLocation() {
		return location;
	}

	public void setLocation(double[] location) {
		this.location = location;
	}

	public double getGenericDiscount() {
		return genericDiscount;
	}

	public void setGenericDiscount(double genericDiscount) {
		this.genericDiscount = genericDiscount;
	}

	public double getSpecialDiscount() {
		return specialDiscount;
	}

	public void setSpecialDiscount(double specialDiscount) {
		this.specialDiscount = specialDiscount;
	}

	public Menu getMenu() {
		return menu;
	}
	public void displayMenu() {
		System.out.println(this.menu.getItems());
	}
	
	/**
	 * Sets a meal as a special offer with the 
	 * special discounted price
	 * @param specialOffer : meal to set as special offer
	 */
	public void setSpecialOffer(Meal specialOffer) {
		MyFoodora app = MyFoodora.getInstance();
		// removing the default discount & applying the special offer discount
		specialOffer.setPrice((1-specialDiscount)*specialOffer.getPrice()/(1-genericDiscount));
		menu.setSpecialOffer(specialOffer);
		app.notifySubscribers(this.getName(), specialOffer);
	}


	public int getnumDeliveredOrders() {
		return numDeliveredOrders;
	}
	public void addDeliveredOrder() {
		this.numDeliveredOrders++;
    }
	@Override
	public String getUserType() {return "restaurant";}

	public OrderSortingPolicy getOrderSortingPolicy() {
		return orderSortingPolicy;
	}

	public void setOrderSortingPolicy(OrderSortingPolicy orderSortingPolicy) {
		this.orderSortingPolicy = orderSortingPolicy;
	}

}
