package core.users;

import java.util.ArrayList;

import core.exceptions.InvalidItemDescription;
import core.exceptions.ItemNotInMenuException;
import core.exceptions.SubscriberAlreadyExistsException;
import core.exceptions.SubscriberNotFoundException;
import core.food.*;

public class Restaurant extends User implements SubscriberObservable{
	private double[] location;
	private Menu menu;
	private double genericDiscount;
	private double specialDiscount;
	private ArrayList<SubscriberObserver> subscribedCustomers;
	private boolean specialOfferAdded=false;
	
	
	public Restaurant(String newUsername, String password) {
		super(newUsername, password);
		this.location = new double[] {0,0};
		
		// Initialize empty menu
		this.menu = new Menu();
		
		// Default discount values
		this.genericDiscount = 0.05;
		this.specialDiscount= 0.1;
		ArrayList<SubscriberObserver>list = new ArrayList<SubscriberObserver>();
		this.subscribedCustomers = list;
	}

	public Restaurant(String newUsername, String password, double[] location) {
		super(newUsername, password);
		this.location = location;
		
		// Initialize empty menu
		this.menu = new Menu();
		
		// Default discount values
		this.genericDiscount = 0.05;
		this.specialDiscount= 0.1;
		ArrayList<SubscriberObserver>list = new ArrayList<SubscriberObserver>();
		this.subscribedCustomers = list;
	}
	
	

	/**
	 * This method adds an item
	 * to the restaurant's menu 
	 * @param itemType : type of the item
	 * @param description : specific array of strings to describe
	 * the item. Order is important as well as certain values.
	 * The array is parsed by the menu object.
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
	
	public void setSpecialOffer(Meal specialOffer) {
		menu.setSpecialOffer(specialOffer);
		specialOfferAdded=true;
		this.notifySubscribers();
	}


	@Override
	public void addSubscriber(SubscriberObserver o) throws SubscriberAlreadyExistsException {
		
            if (subscribedCustomers.contains(o)) {
                throw new core.exceptions.SubscriberAlreadyExistsException("Subscriber already exists " + o);}
            
            subscribedCustomers.add(o);
            System.out.println("Subscriber added : " + o);
      
	}


	@Override
	public void removeSubscriber(SubscriberObserver o) throws SubscriberNotFoundException {
            if (!subscribedCustomers.contains(o)) {
                throw new core.exceptions.SubscriberNotFoundException("Subscriber doesn't exist : " + o);
            }
            subscribedCustomers.remove(o);
            System.out.println("Subscriber removed : " + o);}
		



	@Override
	public void notifySubscribers() {
		if (this.specialOfferAdded) {
			for (SubscriberObserver ob: subscribedCustomers)
				ob.updateSubscriber(this.getSurname(),this.menu.getSpecialOffer());
			this.specialOfferAdded=false;
		}
	}


	public ArrayList<SubscriberObserver> getSubscribedCustomers() {
		return subscribedCustomers;
	}
}
