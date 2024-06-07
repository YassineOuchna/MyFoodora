package core.policies;

import java.util.ArrayList;
import core.comparators.*;

import core.food.MenuItem;

public class ItemSort implements OrderSortingPolicy{

	@Override
	public ArrayList<MenuItem> sort(ArrayList<MenuItem> orders) {
		MenuItemComparator comp= new MenuItemComparator(); 
		orders.sort(comp);
		return orders;
	}
}
