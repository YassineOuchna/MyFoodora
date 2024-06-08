package test.core.policies;

import core.enums.DishCategory;
import core.enums.FoodType;
import core.food.Dish;
import core.food.Meal;
import core.food.MenuItem;
import core.policies.HalfMealSort;
import core.policies.ItemSort;
import core.policies.OrderSortingPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OrderSortingPolicyTest {
    private ArrayList<MenuItem> orders;
    private MenuItem pasta;
    private MenuItem salad;
    private MenuItem potato;
    private Meal halfMeal;
    private Meal halfMeal1;
    private Meal fullMeal;

    @BeforeEach
    void setUp() {
        orders = new ArrayList<>();
        pasta = new Dish("Pasta", 12.0, false, FoodType.STANDARD, DishCategory.MAINDISH);
        salad = new Dish("Salad", 8.0, false, FoodType.VEGETARIAN, DishCategory.STARTER);
        potato = new Dish("potato", 8.0, false, FoodType.VEGETARIAN, DishCategory.STARTER);

        ArrayList<Dish> halfMealDishes = new ArrayList<>();
        halfMealDishes.add((Dish) pasta);
        halfMealDishes.add((Dish) salad);
        halfMeal = new Meal("Half Meal", halfMealDishes);
        
        ArrayList<Dish> halfMealDishes1 = new ArrayList<>();
        halfMealDishes1.add((Dish) pasta);
        halfMealDishes1.add((Dish) potato);
        halfMeal1=new Meal("Half Meal1", halfMealDishes1);

        ArrayList<Dish> fullMealDishes = new ArrayList<>();
        fullMealDishes.add((Dish) pasta);
        fullMealDishes.add((Dish) salad);
        fullMealDishes.add(new Dish("Soup", 5.0, false, FoodType.VEGETARIAN, DishCategory.STARTER));
        fullMeal = new Meal("Full Meal", fullMealDishes);

        
        pasta.orderFrequency = 10;
        salad.orderFrequency = 5;
        halfMeal.orderFrequency = 3;
        halfMeal1.orderFrequency = 17;
        fullMeal.orderFrequency = 8;

        
        orders.add(pasta);
        orders.add(salad);
        orders.add(halfMeal);
        orders.add(fullMeal);
        orders.add(halfMeal1);
    }

    @Test
    void testItemSort() {
        OrderSortingPolicy policy = new ItemSort();
        ArrayList<MenuItem> sortedOrders = policy.sort();

        // Check if the orders are sorted by orderFrequency
        assertEquals("Half Meal", sortedOrders.get(0).getName());
        assertEquals("Salad", sortedOrders.get(1).getName());
        assertEquals("Full Meal", sortedOrders.get(2).getName());


        assertEquals("Pasta", sortedOrders.get(3).getName());
    }

    @Test
    void testHalfMealSort() {
        OrderSortingPolicy policy = new HalfMealSort();
        ArrayList<MenuItem> sortedOrders = policy.sort();

        // Check if only half meals are present and sorted by orderFrequency
        assertEquals(2, sortedOrders.size());
        assertEquals("Half Meal", sortedOrders.get(0).getName());
        assertEquals("Half Meal1", sortedOrders.get(1).getName());
    }
}
