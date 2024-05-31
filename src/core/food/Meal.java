package core.food;

import java.util.ArrayList;

import core.enums.FoodType;
import core.enums.MealSize;

public abstract class Meal extends MenuItem{

	protected ArrayList<Dish> items;
	protected MealSize mealSize;

	public Meal(String itemName, double itemPrice, boolean glutten, FoodType foodtype) {
		super(itemName, itemPrice, glutten, foodtype);
	}
}
