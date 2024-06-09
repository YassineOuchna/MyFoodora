package core.policies;

import core.food.*;
import java.util.ArrayList;
import java.util.Collection;


public interface OrderSortingPolicy {
	/**
	 * Sorts all menu items in the entire 
	 * Myfoodora app
	 * @return a sorted array list of the MenuItems according
	 * to a specific ordering policy
	 */
	public ArrayList<MenuItem> sort();
	
	/**
	 * Sorts specific list of items passed
	 * as argument.
	 * @param items an array list that you wish to sort.
	 * @return a new sorted array list.
	 */
	public ArrayList<MenuItem> sort(Collection<MenuItem> items);

}
