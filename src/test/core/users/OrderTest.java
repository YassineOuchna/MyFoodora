package test.core.users;

import core.enums.DishCategory;
import core.enums.FoodType;
import core.food.Dish;
import core.food.MenuItem;
import core.exceptions.ItemNotInMenuException;
import core.exceptions.ItemNotInOrderException;
import core.orders.Order;
import core.users.Restaurant;
import core.users.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {
    private Restaurant restaurant;
    private Order order;
    private Order newOrder;

    @BeforeEach
    public void setUp() throws Exception{
        restaurant = new Restaurant("restaurantUser", "password");
		String[] pastaDish = new String[] {"Pasta",
				"Maindish", 
				"Standard",
				"0",
				"12.0"
		};
		
		String[] newDish = new String[] {"dish",
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
        restaurant.addMenuItem("dish", newDish);


        // Create a meal
        String[] SaladPastaMeal = new String[] {
        		"Salad and Pasta Meal",
        		saladDish[0],
        		pastaDish[0]
        };
        
        String[] dishPastaMeal = new String[] {
        		"Dish and Pasta Meal",
        		newDish[0],
        		pastaDish[0]
        };
        
        restaurant.getMenu().addItem("meal", SaladPastaMeal);
        restaurant.getMenu().addItem("meal", dishPastaMeal);

        order = new Order(restaurant, "My Order");
        
        Customer customer=new Customer("7amid","password");
        newOrder = new Order(restaurant,"Order1",customer);
    }

    @Test
    public void testAddItemToOrder() throws ItemNotInMenuException {
        MenuItem pasta = restaurant.getMenu().getItem("Pasta");
        order.addItem2Order(pasta);
        order.addItem2Order(pasta);
        assertEquals(2, order.getOrders().get(pasta).intValue());
    }

    @Test
    public void testAddNonExistentItemToOrder() {
        MenuItem nonExistentItem = new Dish("NonExistentDish", 10.0, false, FoodType.VEGETARIAN, DishCategory.MAINDISH);

        assertThrows(ItemNotInMenuException.class, () -> {
            order.addItem2Order(nonExistentItem);
        });
    }

    @Test
    public void testRemoveItemFromOrder() {
        try {
            MenuItem pasta = restaurant.getMenu().getItem("Pasta");
            order.addItem2Order(pasta);
            order.addItem2Order(pasta);
            order.removeItemFromOrder(pasta);

            assertEquals(1, order.getOrders().get(pasta).intValue());
        } catch (ItemNotInMenuException | ItemNotInOrderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRemoveNonExistentItemFromOrder() {
        MenuItem pasta = new Dish("Pasta", 12.0, false, FoodType.STANDARD, DishCategory.MAINDISH);

        assertThrows(ItemNotInOrderException.class, () -> {
            order.removeItemFromOrder(pasta);
        });
    }

    @Test
    public void testGetPrice() {
        try {
            MenuItem pasta = restaurant.getMenu().getItem("Pasta");
            MenuItem salad = restaurant.getMenu().getItem("Salad");

            order.addItem2Order(pasta);
            order.addItem2Order(salad);

            double expectedPrice = pasta.getPrice() + salad.getPrice();
            assertEquals(expectedPrice, order.getPrice());
        } catch (ItemNotInMenuException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetPriceWithMultipleItems() {
        try {
            MenuItem pasta = restaurant.getMenu().getItem("Pasta");
            MenuItem salad = restaurant.getMenu().getItem("Salad");

            order.addItem2Order(pasta);
            order.addItem2Order(pasta);
            order.addItem2Order(salad);

            double expectedPrice = (pasta.getPrice() * 2) + salad.getPrice();
            assertEquals(expectedPrice, order.getPrice());
        } catch (ItemNotInMenuException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testOrderFrequency() throws ItemNotInMenuException {
            MenuItem newDish = restaurant.getMenu().getItem("dish");
            MenuItem meal = restaurant.getMenu().getItem("Dish and Pasta Meal");

            newOrder.addItem2Order(newDish);
            newOrder.addItem2Order(newDish);
            newOrder.addItem2Order(newDish);
            newOrder.addItem2Order(meal);
            newOrder.endOrder();
            assertEquals(3, newDish.orderFrequency);
            assertEquals(1,meal.orderFrequency);}
}
