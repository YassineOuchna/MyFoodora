package test.core.fidelityCards;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.exceptions.InvalidItemDescription;
import core.fidelityCards.BasicFidelityCard;
import core.fidelityCards.PointFidelityCard;
import core.orders.Order;
import core.users.Customer;
import core.users.Restaurant;

class FidelityCardsTest {
	Restaurant restaurant;
	Order order;

	@BeforeEach
	void setUp() throws InvalidItemDescription{
		restaurant = new Restaurant("Test","passWord");
		String[] pastaDish = new String[] {"Pasta",
				"Maindish", 
				"Standard",
				"0",
				"12.0"
		};
		String[] saladDish = new String[] {"Salad",
				"Starter", 
				"vegetarian",
				"0",
				"8.0"
		};

        restaurant.addMenuItem("dish", pastaDish);
        restaurant.addMenuItem("dish", saladDish);

        // Create a meal
        String[] SaladPastaMeal = new String[] {
        		"Salad and Pasta Meal",
        		saladDish[0],
        		pastaDish[0]
        };
        restaurant.getMenu().addItem("meal", SaladPastaMeal);

        order = new Order(restaurant, "My Order");
	}
	@Test
	void basicTierTest() {
		Customer sam = new Customer("Sam", "passwrd");
		assertTrue(sam.getFidelityCard().getClass() == BasicFidelityCard.class);
	}
	
	@Test 
	void pointTierTest() {
		Customer sam = new Customer("Sam", "passwrd");
		sam.registerFidelityCard(new PointFidelityCard());
	}

}







