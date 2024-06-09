package test.core.food;

import core.enums.FoodType;
import core.enums.MealSize;
import core.food.Dish;
import core.food.Meal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MealTest {

    private Meal meal;
    private ArrayList<Dish> dishes;

    @BeforeEach
    public void setUp() {
        dishes = new ArrayList<>();
        Dish dish1 = new Dish("Pizza", 10.99, false, FoodType.VEGETARIAN, null);
        Dish dish2 = new Dish("Salad", 5.99, false, FoodType.VEGETARIAN, null);
        dishes.add(dish1);
        dishes.add(dish2);
        meal = new Meal("Combo Meal", dishes);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("Combo Meal", meal.getName());
        assertEquals(16.98, meal.getPrice());
        assertFalse(meal.isHasGluten());
        assertEquals(FoodType.VEGETARIAN, meal.getFoodType());
        assertEquals(MealSize.HALFMEAL, meal.getMealSize());
        assertEquals(dishes, meal.getDishes());
    }


    @Test
    public void testSetPrice() {
        meal.setPrice(20.99);
        assertEquals(20.99, meal.getPrice());
    }
}
