package test.core.food;

import core.exceptions.InvalidItemDescription;
import core.exceptions.ItemNotInMenuException;
import core.food.Menu;
import core.food.MenuItem;
import core.users.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MenuTest {

    private Menu menu;
    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        restaurant = new Restaurant("Restaurant1", "password");
        menu = new Menu(restaurant);
    }

    @Test
    public void testAddItem() throws InvalidItemDescription, ItemNotInMenuException {
        String[] dishDescription = {"Burger", "MAINDISH", "STANDARD", "1", "10.99"};
        menu.addItem("DISH", dishDescription);
        assertEquals(1, menu.getItems().size());

        String[] mealDescription = {"Meal1", "Burger"};
        menu.addItem("MEAL", mealDescription);
        assertEquals(2, menu.getItems().size());
    }

    @Test
    public void testAddItemInvalidType() {
        String[] description = {"Burger", "MAIN", "STANDARD", "1", "10.99"};
        assertThrows(InvalidItemDescription.class, () -> menu.addItem("InvalidType", description));
    }

    @Test
    public void testAddItemInvalidDishDescription() {
        String[] invalidDishDescription = {"Burger", "MAIN", "STANDARD", "10.99"};
        assertThrows(InvalidItemDescription.class, () -> menu.addItem("DISH", invalidDishDescription));
    }

    @Test
    public void testGetItem() throws InvalidItemDescription, ItemNotInMenuException {
        String[] dishDescription = {"Burger", "MAINDISH", "STANDARD", "1", "10.99"};
        menu.addItem("DISH", dishDescription);
        MenuItem item = (MenuItem) menu.getItem("Burger");
        assertEquals("Burger", item.getName());
    }

    @Test
    public void testGetItemNotInMenu() {
        assertThrows(ItemNotInMenuException.class, () -> menu.getItem("NonExistentItem"));
    }

    @Test
    public void testRemoveItem() throws InvalidItemDescription, ItemNotInMenuException {
        String[] dishDescription = {"Burger", "MAINDISH", "STANDARD", "1", "10.99"};
        menu.addItem("DISH", dishDescription);
        assertEquals(1, menu.getItems().size());
        menu.removeItem("Burger");
        assertEquals(0, menu.getItems().size());
    }

    @Test
    public void testRemoveItemNotInMenu() {
        assertThrows(ItemNotInMenuException.class, () -> menu.removeItem("NonExistentItem"));
    }
}
