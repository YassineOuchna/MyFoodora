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
	 * Adds a point for each 10 euros spent.
	 * If points exceed 100, a 10% discount is applied.
	 */
	@Override
	public void updateCard(Order order) {
		// Setting discount back to zero
		fidelityDiscount = 0;

		// Updating points & discount accordingly
		double total = order.getPrice();
		this.addPoints((int) total/10);
		if (points >= 100) {
			fidelityDiscount = 0.1;
			points = points - 100;
		}
	}
	
	
	public double getFidelityDiscount() {
		return fidelityDiscount;
	}

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
