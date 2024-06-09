package test.core.food;

import core.enums.DishCategory;
import core.enums.FoodType;
import core.food.Dish;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DishTest {

    private Dish dish;

    @BeforeEach
    public void setUp() {
        dish = new Dish("Pizza", 10.99, false, FoodType.VEGETARIAN, DishCategory.MAINDISH);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("Pizza", dish.getName());
        assertEquals(10.99, dish.getPrice());
        assertFalse(dish.isHasGluten());
    }

    @Test
    public void testToString() {
        String expected = "MAINDISH : Pizza, gluten free, VEGETARIAN, 10.99$";
        assertEquals(expected, dish.toString());
    }

    @Test
    public void testSetDishCategory() {
        dish.setDishCategory(DishCategory.DESSERT);
        assertEquals(DishCategory.DESSERT, dish.getDishCategory());
    }

    @Test
    public void testSetPrice() {
        dish.setPrice(15.99);
        assertEquals(15.99, dish.getPrice());
    }
}
