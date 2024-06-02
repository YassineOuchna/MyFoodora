package core.users;

import java.util.ArrayList;

import core.enums.DishCategory;
import core.enums.FoodType;
import core.exceptions.ItemNotInMenuException;
import core.food.*;

public class Restaurant extends User{
	private double[] location;
	private Menu menu;
	private double genericDiscount;
	private double specialDiscount;
	private MenuItemFactoryProducer menuItemFactoryProducer;

	public Restaurant(String newUsername, String password, double[] location) {
		super(newUsername, password);
		this.location = location;
		this.menuItemFactoryProducer = new MenuItemFactoryProducer();
		
		// Initialize empty menu
		this.menu = new Menu();
		
		// Default discount values
		this.genericDiscount = 0.05;
		this.specialDiscount= 0.1;
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
	 * @throws an ItemNotInMenuException if the meal isn't in the menu
	 */
	public void addMeal(String name, ArrayList<String> dishNames) throws ItemNotInMenuException{

		// Dish objects corresponding to dishNames
		ArrayList<Dish> dishes = new ArrayList<Dish>();
		for (String dishName : dishNames) {
			if (!this.menu.getDishes().containsKey(dishName)) {
				throw new ItemNotInMenuException("Can't add meal : " + dishName + " not in Menu");
			}
			dishes.add(menu.getDishes().get(dishName));
		}
		MenuItemFactory mealFactory = menuItemFactoryProducer.getFactory("Meal");
		Meal newMeal = mealFactory.createMeal(name, dishes);
		menu.addMeal(newMeal);
	}
	
	/**
	 * Removes the dish from the menu
	 * @param dishName : name of the dish to remove
	 * @throws an ItemNotInMenuException if the meal isn't in the menu
	 */
	public void removeDish(String dishName) throws ItemNotInMenuException {
		
		// Checking if dishName exists in the menu
		if (!this.menu.getDishes().containsKey(dishName)) {
			throw new ItemNotInMenuException("Can't remove dish : " + dishName + " not in Menu");
		}
		this.menu.removeDish(dishName);
	}
	
	/**
	 * Removes the meal from the menu
	 * @param mealName: name of the meal to remove
	 * @throws an ItemNotInMenuException if the meal isn't in the menu
	 */
	public void removeMeal(String mealName) throws ItemNotInMenuException {
		// Checking if dishName exists in the menu
		if (!this.menu.getMeals().containsKey(mealName)) {
			throw new ItemNotInMenuException("Can't remove meal : " + mealName + " not in Menu");
		}
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
	
	
	

}
