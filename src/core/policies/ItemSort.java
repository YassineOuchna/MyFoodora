package core.policies;

import java.util.ArrayList;
import java.util.Collection;

import core.MyFoodora;
import core.comparators.*;

import core.food.MenuItem;
import core.users.Restaurant;

public class ItemSort implements OrderSortingPolicy{

	@Override
	public ArrayList<MenuItem> sort() {
		MyFoodora app = MyFoodora.getInstance();
		MenuItemComparator comp= new MenuItemComparator(); 
		ArrayList<MenuItem> items = new ArrayList<MenuItem>();

		for(Restaurant r: app.getRestaurants()){
			for (MenuItem item : r.getMenu().getItems().values()) {
					items.add(item);
			}
		}
		items.sort(comp);
	return items;
	}

	@Override
	public ArrayList<MenuItem> sort(Collection<MenuItem> items) {
		MenuItemComparator comp= new MenuItemComparator(); 
		ArrayList<MenuItem> sortedItems = (ArrayList<MenuItem>) items;
		sortedItems.sort(comp);
	return sortedItems;
	}
}
