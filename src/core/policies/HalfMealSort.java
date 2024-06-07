package core.policies;

import java.util.ArrayList;
import core.comparators.*;

import core.enums.MealSize;
import core.food.Meal;
import core.food.MenuItem;

public class HalfMealSort implements OrderSortingPolicy{

	@Override
	public ArrayList<MenuItem> sort(ArrayList<MenuItem> orders) {
		MenuItemComparator comp= new MenuItemComparator();
		ArrayList<MenuItem> halfMeals= new ArrayList<MenuItem>();
		
		for (MenuItem item: orders) {
			if (item instanceof Meal) {
				Meal meal= (Meal) item;
				if (meal.getMealSize()==MealSize.HALFMEAL) {
					halfMeals.add(item);
				}
			};
		}
		halfMeals.sort(comp);
		return halfMeals;
		}
}
