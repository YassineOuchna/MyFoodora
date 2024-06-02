package test.core.users;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.enums.DishCategory;
import core.enums.FoodType;
import core.exceptions.ItemNotInMenuException;
import core.food.Menu;
import core.users.Restaurant;

class RestaurantTest {

	Restaurant restaurant;
	@BeforeEach 
	void initializeRestaurant() {
		restaurant = new Restaurant("MickyDeez", "mickyPassword" , new double[] {15, 22});
	}

	@Test
	void testAddDish() {
		restaurant.addDish("Big Mac", 7.99, true, FoodType.STANDARD, DishCategory.MAINDISH);
		restaurant.addDish("Chicken Nuggies", 2, true, FoodType.STANDARD, DishCategory.STARTER);
		restaurant.addDish("Mcflurry", 3, true, FoodType.STANDARD, DishCategory.DESSERT);
		Menu menu = restaurant.getMenu();
		System.out.println(menu);
		assertTrue(menu.getDishes().containsKey("Big Mac"));
		assertTrue(menu.getDishes().containsKey("Chicken Nuggies"));
		assertTrue(menu.getDishes().containsKey("Mcflurry"));
	}

	@Test
	void testAddMeal() {

		// Adding dishes first 
		restaurant.addDish("Big Mac", 7.99, true, FoodType.STANDARD, DishCategory.MAINDISH);
		restaurant.addDish("Chicken Nuggies", 2, true, FoodType.STANDARD, DishCategory.STARTER);
		restaurant.addDish("Mcflurry", 3, true, FoodType.STANDARD, DishCategory.DESSERT);
		
		// Getting names of dishes to compose the meal
		ArrayList<String> dishNames = new ArrayList<String>();
		dishNames.add("Big Mac");
		dishNames.add("Chicken Nuggies");
		dishNames.add("Mcflurry");
		
		// Adding should go through
		try {
			restaurant.addMeal("Big Mac Menu", dishNames);
		} catch (Exception e) {
		}

		Menu menu = restaurant.getMenu();
		System.out.println(menu);
		assertTrue(menu.getMeals().containsKey("Big Mac Menu"));
		
		// Testing adding a meal with non Existent dishes
		ArrayList<String> wrongDishNames = new ArrayList<String>();
		wrongDishNames.add("Chicken Nuggies");
		wrongDishNames.add("Dish6977");
		assertThrows(ItemNotInMenuException.class, () -> restaurant.addMeal("meal21", wrongDishNames));

	}

	@Test
	void testRemoveDish() {
		restaurant.addDish("Dish0", 7.99, true, FoodType.STANDARD, DishCategory.MAINDISH);
		try {
			restaurant.removeDish("Dish0");
		} catch (Exception e) {
		}
		Menu menu = restaurant.getMenu();
		assertFalse(menu.getDishes().containsKey("Dish0"));
	}

	@Test 
	void testRemoveDishNotInMenu() {
		assertThrows(ItemNotInMenuException.class, () -> restaurant.removeDish("Dish57"));
	}

	@Test
	void testRemoveMeal() {
		restaurant.addDish("dish1", 0, false, null, null);
		restaurant.addDish("dish2", 0, false, null, null);
		restaurant.addDish("dish3", 0, false, null, null);
		ArrayList<String> dishNames = new ArrayList<String>();
		dishNames.add("dish1");
		dishNames.add("dish2");
		try {
			restaurant.addMeal("meal0", dishNames);
		} catch (Exception e) {
		}
		
		System.out.println(restaurant.getMenu());
		try {
			restaurant.removeMeal("meal0");
		} catch (Exception e) {
		}
		Menu menu = restaurant.getMenu();
		assertFalse(menu.getMeals().containsKey("meal0"));
	}
	
	@Test
	void testRemoveMealNotInMenu() {
		assertThrows(ItemNotInMenuException.class, () -> restaurant.removeMeal("Meal57"));
	}
}
