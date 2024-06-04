package test.core.users;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.exceptions.InvalidItemDescription;
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
		mcflurryDesc= new String[] {"Mcflurry",
				"dessert", 
				"Standard",
				"0",
				"3"
		};
	}

	@Test
	void testAddDish() throws InvalidItemDescription{
		restaurant.addMenuItem("dish", mcflurryDesc);
		restaurant.addMenuItem("dish", bigMacDesc);
		restaurant.addMenuItem("dish", ChickenNuggiesDesc);
		Menu menu = restaurant.getMenu();
		assertTrue(menu.getItems().containsKey("Big Mac"));
		assertTrue(menu.getItems().containsKey("Chicken Nuggies"));
		assertTrue(menu.getItems().containsKey("Mcflurry"));
	}

	@Test
	void testAddMeal() throws InvalidItemDescription{

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
		
		// Adding should go through (no exception)
		try {
			restaurant.addMenuItem("meal", mealDesc);
		} catch (Exception e) {
		}

		Menu menu = restaurant.getMenu();
		assertTrue(menu.getItems().containsKey("Big Mac Meal"));
		
		
		// Testing adding a meal with non Existent dishes
		String[] wrongDishNames = new String[] {
		"Chicken Nuggies", "Dish9677"};
		assertThrows(InvalidItemDescription.class, () -> restaurant.addMenuItem("meal21", wrongDishNames));
	}

	@Test
	void testRemoveMenuItem() throws InvalidItemDescription, ItemNotInMenuException {
		restaurant.addMenuItem("dish", mcflurryDesc);
		restaurant.removeItem("Mcflurry");
		Menu menu = restaurant.getMenu();
		assertFalse(menu.getItems().containsKey("Dish0"));
	}

	@Test 
	void testRemoveItemNotInMenu() {
		assertThrows(ItemNotInMenuException.class, () -> restaurant.removeItem("Dish57"));
	}

}
