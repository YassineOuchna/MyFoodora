package test.core.fidelityCards;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.MyFoodora;
import core.exceptions.CourierNotFoundException;
import core.exceptions.InvalidItemDescription;
import core.exceptions.ItemNotInMenuException;
import core.fidelityCards.BasicFidelityCard;
import core.fidelityCards.LotteryFidelityCard;
import core.fidelityCards.PointFidelityCard;
import core.food.MenuItem;
import core.orders.Order;
import core.users.Courier;
import core.users.Customer;
import core.users.Restaurant;

class FidelityCardsTest {
	Restaurant restaurant;
	Order order;
	Customer sam;

	@BeforeEach
	void setUp() throws InvalidItemDescription{
		restaurant = new Restaurant("Test","passWord");
		sam = new Customer("Sam", "passwrd");
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
				"8.5"
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
        Courier c = new Courier("courier", "pessward");
        c.setOnDuty(true);
        MyFoodora.getInstance().addUser(c);

        order = new Order(restaurant, "Sam's order", sam);
	}
	@Test
	void basicTierTest() {
		assertTrue(sam.getFidelityCard().getClass() == BasicFidelityCard.class);
	}
	
	@Test 
	void pointTierTestInitialization() throws ItemNotInMenuException{
		PointFidelityCard pointCard = new PointFidelityCard();
		sam.registerFidelityCard(pointCard);
		// testing initialization
		assertTrue(pointCard.getPoints() == 0);
		assertTrue(pointCard.getFidelityDiscount() == 0);
		
	}
	@Test
	void pointTierTestPointsIncrement() throws ItemNotInMenuException, CourierNotFoundException{
		PointFidelityCard pointCard = new PointFidelityCard();
		sam.registerFidelityCard(pointCard);
		// Testing points increment with a meal > 10e in price
		MenuItem saladMeal = restaurant.getMenu().getItem("Salad and Pasta Meal");
		order.addItem2Order(saladMeal);
		order.endOrder();
		// a salad meal is 20.5e - 5% discount = 19.025
		// so 1 point is expected
		assertEquals(1, pointCard.getPoints());
	}
	
	@Test
	void pointTierTestDiscount() throws ItemNotInMenuException, CourierNotFoundException{
		PointFidelityCard pointCard = new PointFidelityCard();
		sam.registerFidelityCard(pointCard);

		// Testing fidelity discount being 10% when reaching 100 points
		MenuItem saladMeal = restaurant.getMenu().getItem("Salad and Pasta Meal");

		// A salad meal is 20.5 euros - 5% disc = 19.475 = 1 points
		// sam pays 19.475 x 54 = 1051.65 euros ~ 105 points
		for (int i = 0; i < 54; i++) {
			order.addItem2Order(saladMeal);
		}
		order.endOrder();

		// 102 points turns into 10% discount + 2 points
		assertEquals(pointCard.getFidelityDiscount(),0.1);
		assertEquals(pointCard.getPoints(),5);
	}
	
	@Test
	void lotteryTierTest() {
		LotteryFidelityCard lotCard = new LotteryFidelityCard(2);
		sam.registerFidelityCard(lotCard);
		sam.getFidelityCard().updateCard(null);
		assertTrue(sam.getFidelityCard().getClass() == LotteryFidelityCard.class);
		while (!(sam.getFidelityCard().getFidelityDiscount() == 1)) {
			sam.unregisterFidelityCard();
			sam.registerFidelityCard(new LotteryFidelityCard(2));
			sam.getFidelityCard().updateCard(null);
		}
		// Assuring at some point the lottery is won
		assertEquals(1, sam.getFidelityCard().getFidelityDiscount());
		
	}

} 





