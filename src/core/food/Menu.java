package core.food;

import java.util.HashMap;

import core.exceptions.ItemNotInMenuException;

public class Menu {
	
	private HashMap<String, Dish> dishes;
	private HashMap<String, Meal> meals;
	
	private Meal specialOffer;
	
	public Menu() {
		this.dishes = new HashMap<String, Dish>();
		this.meals = new HashMap<String, Meal>();
		this.specialOffer = null;
	}
	
	@Override
	public String toString() {
		String result = "Dishes : \n";
		for (Dish dish : dishes.values()) {
			result = result + dish + "\n";
		}
		result += "Meals  : \n";

		for (Meal meal : meals.values()) {
			result = result + meal + "\n";
		}
		result = result + "Special Offer : " + specialOffer;
		return result;
	}

	
	/**
	 * Getter for a specific dish in the menu.
	 * @param dishName : name of the dish to return 
	 * @throws ItemNotInMenuException if dish isn't in the menu
	 */
	public Dish getDish(String dishName) throws ItemNotInMenuException{
		if (!dishes.containsKey(dishName)) {
			throw new ItemNotInMenuException("" + dishName + " not in Menu");
		}
		return dishes.get(dishName);
	}

	/**
	 * Getter for a specific dish in the menu.
	 * @param dishName : name of the dish to return 
	 * @throws ItemNotInMenuException if dish isn't in the menu
	 */
	public Meal getMeal(String mealName) throws ItemNotInMenuException{
		if (!dishes.containsKey(mealName)) {
			throw new ItemNotInMenuException("" + mealName + " not in Menu");
		}
		return meals.get(mealName);
	}

		
	public HashMap<String, Dish> getDishes() {
		return dishes;
	}

	public void addDish(Dish dish) {
		this.dishes.put(dish.getName(), dish);
	}
	
	/**
	 * Removes the dish from the menu 
	 * @param dishName :  a valid dish name that is in the menu
	 */
	public void removeDish(String dishName) {
		dishes.remove(dishName);
	}

	public HashMap<String, Meal> getMeals() {
		return meals;
	}

	public void addMeal(Meal meal) {
		this.meals.put(meal.getName(), meal);
	}
	
	public void removeMeal(String mealName) {
		meals.remove(mealName);
	}

	public Meal getSpecialOffer() {
		return specialOffer;
	}

	public void setSpecialOffer(Meal specialOffer) {
		this.specialOffer = specialOffer;
	}
}
