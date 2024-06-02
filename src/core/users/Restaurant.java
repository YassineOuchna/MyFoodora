package core.users;

import java.util.ArrayList;

import core.enums.DishCategory;
import core.enums.FoodType;
import core.exceptions.SubscriberAlreadyExistsException;
import core.exceptions.SubscriberNotFoundException;
import core.food.*;

public class Restaurant extends User implements SubscriberObservable{
	private double[] location;
	private Menu menu;
	private double genericDiscount;
	private double specialDiscount;
	private MenuItemFactoryProducer menuItemFactoryProducer;
	private ArrayList<SubscriberObserver> subscribedCustomers;
	private boolean specialOfferAdded=false;

	public Restaurant(String newUsername, String password) {
		super(newUsername, password);
		this.menuItemFactoryProducer = new MenuItemFactoryProducer();
		ArrayList<SubscriberObserver>list = new ArrayList<SubscriberObserver>();
		this.subscribedCustomers = list;
		this.menu= new Menu();
	}
	
	

	/**
	 * This method adds a dish 
	 * to the restaurant's menu 
	 * @param name : name of the dish
	 * @param price : price of the dish
	 * @param foodType : enum specifying vegetarian or standard dish
	 * @param dishCategory : enum main dish, entry or dessert 
	 * @param hasGluten : boolean specifying if it contains gluten 
	 */
	public void addDish(String name, double price, boolean hasGluten, FoodType foodType, DishCategory dishCategory) {
		MenuItemFactory dishFactory = menuItemFactoryProducer.getFactory("Dish");
		Dish newDish = dishFactory.createDish(name, price, hasGluten, foodType, dishCategory);
		menu.addDish(newDish);
	}

	/**
	 * This method adds a meal
	 * to the restaurant's menu. 
	 * @param name : name of the dish
	 * @param dishes : ArrayList of names of dishes composing the meal
	 * @param mealSize : enum specifying if its a full meal or half meal 
	 * @param foodType : enum specifying vegetarian or standard dish
	 * @param dishCategory : enum main dish, entry or dessert 
	 * @param hasGluten : boolean specifying if it contains gluten 
	 * If one of the dishes of the meal doesn't exist in the menu
	 * it throws an exception
	 */
	public void addMeal(String name, ArrayList<String> dishNames) {
		// Dish objects corresponding to dishNames
		ArrayList<Dish> dishes = new ArrayList<Dish>();
		boolean dishExists = true;
		for (String dishName : dishNames) {
			if (!this.menu.getDishes().containsKey(dishName)) {
				System.out.println("Dishes inserted not available in the menu");
				dishExists = false;
				break;
			} else {
				dishes.add(menu.getDishes().get(dishName));
			}
		}
		if (dishExists) {
			MenuItemFactory mealFactory = menuItemFactoryProducer.getFactory("Meal");
			Meal newMeal = mealFactory.createMeal(name, dishes);
			menu.addMeal(newMeal);
		}
	}
	
	/**
	 * Removes the dish from the menu
	 * @param dishName : name of the dish to remove
	 */
	public void removeDish(String dishName) {
		this.menu.removeDish(dishName);
	}
	
	/**
	 * Removes the meal from the menu
	 * @param mealName: name of the meal to remove
	 */
	public void removeMeal(String mealName) {
		this.menu.removeMeal(mealName);
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
				ob.updateSubscriber(this.surname,this.menu.getSpecialOffer());
			this.specialOfferAdded=false;
		}
	}


	public ArrayList<SubscriberObserver> getSubscribedCustomers() {
		return subscribedCustomers;
	}
}
