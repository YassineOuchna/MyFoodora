package core.food;

import java.util.Collection;
import java.util.HashMap;

public class Menu {
	
	private HashMap<String, Dish> dishes;
	private HashMap<String, Meal> meals;
	
	private Meal specialOffer;
	
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
		result = result + specialOffer;
		return result;
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
