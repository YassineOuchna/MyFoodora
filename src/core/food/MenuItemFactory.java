package core.food;

import java.util.ArrayList;

import core.enums.DishCategory;
import core.enums.FoodType;

public interface MenuItemFactory {
	
	public Meal createMeal(String name, ArrayList<Dish> dishes);
	public Dish createDish(String name, double price, boolean hasGluten, FoodType foodType, DishCategory dishCategory);

}
