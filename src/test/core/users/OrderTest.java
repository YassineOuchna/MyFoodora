package test.core.users;

import core.enums.DishCategory;
import core.enums.FoodType;
import core.food.Dish;
import core.food.Meal;
import core.food.MenuItem;
import core.exceptions.ItemNotInMenuException;
import core.exceptions.ItemNotInOrderException;
import core.orders.Order;
import core.users.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {
    private Restaurant restaurant;
    private Order order;

    @BeforeEach
    public void setUp() throws ItemNotInMenuException {
        restaurant = new Restaurant("restaurantUser", "password");
        restaurant.addDish("Pasta", 12.0, false, FoodType.STANDARD, DishCategory.MAINDISH);
        restaurant.addDish("Salad", 8.0, false, FoodType.VEGETARIAN, DishCategory.STARTER);

        // Create a meal
        ArrayList<Dish> dishes = new ArrayList<>();
        dishes.add(restaurant.getMenu().getDish("Pasta"));
        dishes.add(restaurant.getMenu().getDish("Salad"));
        Meal meal = new Meal("Pasta and Salad Meal", dishes);
        restaurant.getMenu().addMeal(meal);

        order = new Order(restaurant, "My Order");
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
}
