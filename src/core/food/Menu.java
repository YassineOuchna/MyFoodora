package core.food;

import java.util.ArrayList;
import java.util.HashMap;

import core.enums.DishCategory;
import core.enums.FoodType;
import core.exceptions.InvalidItemDescription;
import core.exceptions.ItemNotInMenuException;
import core.users.Restaurant;

public class Menu {
	
	private HashMap<String,MenuItem> items;
	private Restaurant restaurant;
	
	

	private Meal specialOffer;
	
	/**
	 * Creates a menu linked to the
	 * specified restaurant.
	 * @param restaurant : a restaurant.
	 */
	public Menu(Restaurant restaurant) {
		this.restaurant = restaurant;
		this.items=new HashMap<String,MenuItem>();
		this.specialOffer = null;
	}

	
	@Override
	public String toString() {
		String result = "Menu contents :";
		for (MenuItem item : items.values()) {
			result = result + item + "\n";
		}

		result = result + "Special Offer : " + specialOffer;
		return result;
	}
	
	/**
	 * Adds an item to the 
	 * restaurant's menu and applying corresponding discounts
	 * @param itemType : Type the type of the item
	 * @param description : an array of strings with necessary information
	 * to describe the item. 
	 * @throws ItemNotInMenuException when adding an item like a meal 
	 * that is composed of non existent "atomic" items in the menu.
	 */
	public void addItem(String itemType, String[] description) throws InvalidItemDescription{
		// Checking type of item 
		if (itemType.equalsIgnoreCase("DISH")) {
			if (description.length == 5) {
			String name = description[0];
			DishCategory dishCategory = DishCategory.valueOf(description[1].toUpperCase());
			FoodType foodType = FoodType.valueOf(description[2].toUpperCase());
			boolean hasGluten = (description[3] == "1");
			double price = Double.valueOf(description[4]);
			Dish dish = new Dish(name, price, hasGluten, foodType, dishCategory);
			items.put(name, dish);
			} else {
				throw new InvalidItemDescription("Incorrect length for the description");
			}
		} else if (itemType.equalsIgnoreCase("MEAL")) {
			String name = description[0];
			ArrayList<Dish> dishList = new ArrayList<Dish>();
			int n = description.length;
			// looping through remaining description
			for (int i=1; i<n; i++) {
				String dishName = description[i];
				// Checking if the dishes are in the menu
				if (!items.containsKey(dishName)) {
					throw new InvalidItemDescription("Invalid dish name : " + dishName+ " Not in menu");
				}
				
				dishList.add((Dish) items.get(dishName));
			}
			
			Meal meal = new Meal(name, dishList);
			// Applying generic discount 
			meal.setPrice(meal.getPrice()*(1 - restaurant.getGenericDiscount()));
			items.put(name, meal);
		} else {
			throw new InvalidItemDescription("Invalid item type : " + itemType + " Not in menu");
		}
	}

	/**
	 * Getter for a specific item in the menu.
	 * @param itemName : name of the item to return.
	 * @throws ItemNotInMenuException if the item isn't in the menu
	 */
	public MenuItem getItem(String itemName) throws ItemNotInMenuException{
		if (!items.containsKey(itemName)) {
			throw new ItemNotInMenuException("" + itemName + " not in Menu");
		}
		return items.get(itemName);
	}

		
	/**
	 * Removes the item from the menu 
	 * @param itemName : item name to remove.
	 * @throws ItemNotInMenuException if the item isn't in the menu
	 */
	public void removeItem(String itemName) throws ItemNotInMenuException {

		if (!items.containsKey(itemName)) {
			throw new ItemNotInMenuException("" + itemName + " not in Menu");
		}
		items.remove(itemName);
	}

	public Meal getSpecialOffer() {
		return specialOffer;
	}

	public void setSpecialOffer(Meal specialOffer) {
		this.specialOffer = specialOffer;
	}
	
	public HashMap<String, MenuItem> getItems() {
		return items;
	}


	
}
