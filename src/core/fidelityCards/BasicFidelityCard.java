package core.fidelityCards;

import core.orders.Order;

public class BasicFidelityCard extends FidelityCard{

	public BasicFidelityCard() {
		super();
		fidelityDiscount = 0;
	}

	@Override
	public String toString() {
		return "Basic Fidelity Card";
	}
	@Override
	public void updateCard(Order order) {
	}

}
