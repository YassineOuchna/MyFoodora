package core.food;

import java.util.Comparator;

public class MenuItemComparator implements Comparator<MenuItem>{

	@Override
	public int compare(MenuItem item1,MenuItem item) {
		//return item1.orderFrequency- item.orderFrequency;
		return Integer.compare(item1.orderFrequency, item.orderFrequency);
	}

}
