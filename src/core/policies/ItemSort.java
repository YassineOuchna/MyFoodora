package core.policies;

import java.util.ArrayList;

import core.food.MenuItem;
import core.food.MenuItemComparator;

public class ItemSort implements OrderSortingPolicy{

	@Override
	public ArrayList<MenuItem> sort(ArrayList<MenuItem> orders) {
		MenuItemComparator comp= new MenuItemComparator(); 
		orders.sort(comp);
		return orders;
	}
}
