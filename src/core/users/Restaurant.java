package core.users;


import core.MyFoodora;
import core.exceptions.InvalidItemDescription;
import core.exceptions.ItemNotInMenuException;
import core.food.*;

public class Restaurant extends User {
	private double[] location;
	private Menu menu;
	private double genericDiscount;
	private double specialDiscount;
	
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

}
