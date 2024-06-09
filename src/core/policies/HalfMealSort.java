package core.policies;

import java.util.ArrayList;

import core.MyFoodora;
import core.comparators.*;

import core.enums.MealSize;
import core.food.Meal;
import core.food.MenuItem;
import core.users.Restaurant;

public class HalfMealSort implements OrderSortingPolicy{

	@Override
	public ArrayList<MenuItem> sort() {
		MyFoodora app = MyFoodora.getInstance();
		MenuItemComparator comp= new MenuItemComparator();
		ArrayList<MenuItem> halfMeals= new ArrayList<MenuItem>();
		
		for (Restaurant r: app.getRestaurants()) {
			for (MenuItem item : r.getMenu().getItems().values()) {
				if (item instanceof Meal) {
					Meal meal= (Meal) item;
					if (meal.getMealSize()==MealSize.HALFMEAL) {
						halfMeals.add((MenuItem) meal);
					}
				}
			}
		}
		halfMeals.sort(comp);
		return halfMeals;
	}

}