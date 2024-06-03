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
	String[] bigMacDesc;
	String[] ChickenNuggiesDesc;
	String[] mcflurryDesc;
	@BeforeEach 
	void initializeRestaurant() {
		restaurant = new Restaurant("MickyDeez", "mickyPassword" , new double[] {15, 22});
		bigMacDesc = new String[] {"Big Mac",
				"maindish", 
				"Standard",
				"1",
				"7.99"
		};
		ChickenNuggiesDesc = new String[] {"Chicken Nuggies",
				"Starter", 
				"Standard",
				"1",
				"2"
		};
		mcflurryDesc= new String[] {"mcflurry",
				"dessert", 
				"Standard",
				"0",
				"3"
		};
	}

	@Test
	void testAddDish() {
		restaurant.addMenuItem("dish", mcflurryDesc);
		restaurant.addMenuItem("dish", bigMacDesc);
		restaurant.addMenuItem("dish", ChickenNuggiesDesc);
		Menu menu = restaurant.getMenu();
		assertTrue(menu.getItems().containsKey("Big Mac"));
		assertTrue(menu.getItems().containsKey("Chicken Nuggies"));
		assertTrue(menu.getItems().containsKey("Mcflurry"));
	}

	@Test
	void testAddMeal() {

		// Adding dishes first 
		restaurant.addMenuItem("dish", mcflurryDesc);
		restaurant.addMenuItem("dish", bigMacDesc);
		restaurant.addMenuItem("dish", ChickenNuggiesDesc);
		
		// Description array of meal 
		String[] mealDesc = new String[] {"Big Mac Meal",
				mcflurryDesc[0],
				bigMacDesc[0],
				ChickenNuggiesDesc[0]
		};
		
		// Adding should go through
		try {
			restaurant.addMenuItem("meal", mealDesc);
		} catch (Exception e) {
		}

		Menu menu = restaurant.getMenu();
		assertTrue(menu.getItems().containsKey("Big Mac Menu"));
		
		// Testing adding a meal with non Existent dishes
		ArrayList<String> wrongDishNames = new ArrayList<String>();
		wrongDishNames.add("Chicken Nuggies");
		wrongDishNames.add("Dish6977");
		assertThrows(ItemNotInMenuException.class, () -> restaurant.addMenuItem("meal21", wrongDishNames));

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
