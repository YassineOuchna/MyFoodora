package core.comparators;

import java.util.Comparator;

import core.users.Restaurant;

public class RestaurantComparator implements Comparator<Restaurant>{
	
	@Override
	public int compare(Restaurant r1, Restaurant r2) {
		return Integer.compare(r1.getnumDeliveredOrders(), r2.getnumDeliveredOrders());
	}

	
	

}
