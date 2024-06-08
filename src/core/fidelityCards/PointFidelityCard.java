package core.fidelityCards;

import core.orders.Order;

public class PointFidelityCard extends FidelityCard{
	private int points;
	
	public PointFidelityCard() {
		super();
		name = "Point Fidelity Card";
		points = 0;
		fidelityDiscount = 0;
	}
	

	/**
	 * Updates the fidelity discounting 
	 * according to the new order passed as argument :
	 * Resets the fidelity discount to 0 
	 * Adds a point for each 10 euros spent in the new order.
	 * If points exceed 100, new fidelity discount becomes 10%. 
	 * @param newOrder : takes a new order done by the customer. 
	 */
	@Override
	public void updateCard(Order newOrder) {
		// Setting discount back to zero
		fidelityDiscount = 0;

		// Updating points & discount accordingly
		double total = newOrder.getPrice();
		this.addPoints((int) total/10);
		if (points >= 100) {
			fidelityDiscount = 0.1;
			points = points - 100;
		}
	}
	
	/**
	 * Adds points to the fidelity card.
	 * @param newPoints : an integer representing 
	 * the amount of points to be added.
	 */
	public void addPoints(int newPoints) {
		points = points + newPoints;
	}
	
	public int getPoints() {
		return points;
	}
	
	
	public String toString() {
		return "" + name + "\nPoints : " + points +
				"\nDiscount on next order : "+ fidelityDiscount + "%";
	}


}
