package core.fidelityCards;

import core.orders.Order;

public abstract class FidelityCard{
	
	/**
	 * Each fidelity plan has a name 
	 */
	protected String name;
	
	/**
	 * Fidelity discount for customers applied
	 * as follows : finalPrice = (1 - fidelityDiscount) * originalPrice
	 */
	protected double fidelityDiscount;
	
	/**
	 * Updates characteristics of the card
	 * based on a finished order by the customer.
	 * This function must be called after each order.
	 * @param order : order ordered by the customer holding
	 * the fidelity card.
	 */
	public abstract void updateCard(Order order);

}
