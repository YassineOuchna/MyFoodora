package core.food;

import java.util.ArrayList;

import core.enums.DishCategory;
import core.enums.FoodType;

public class DishFactory implements MenuItemFactory{

	@Override
	public Meal createMeal(String name, ArrayList<Dish> dishes) {
		return null;
	}

	@Override
	public Dish createDish(String name, double price, boolean hasGluten, FoodType foodType, DishCategory dishCategory) {
		return new Dish(name, price, hasGluten, foodType, dishCategory);
	}

}
