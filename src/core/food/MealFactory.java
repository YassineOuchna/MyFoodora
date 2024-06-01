package core.food;

import java.util.ArrayList;

import core.enums.DishCategory;
import core.enums.FoodType;

public class MealFactory implements MenuItemFactory{

	@Override
	public Meal createMeal(String name, ArrayList<Dish> dishes) {
		return new Meal(name, dishes);
	}

	@Override
	public Dish createDish(String name, double price, boolean hasGluten, FoodType foodType, DishCategory dishCategory) {
		return null;
	}

}
