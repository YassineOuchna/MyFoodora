package core.comparators;

import java.util.Comparator;

import core.food.MenuItem;

public class MenuItemComparator implements Comparator<MenuItem>{

	@Override
	public int compare(MenuItem item1,MenuItem item) {
		//return item1.orderFrequency- item.orderFrequency;
		return Integer.compare(item1.orderFrequency, item.orderFrequency);
	}

}
