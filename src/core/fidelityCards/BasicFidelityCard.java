package core.fidelityCards;

import core.orders.Order;

public class BasicFidelityCard extends FidelityCard{

	public BasicFidelityCard() {
		super();
		fidelityDiscount = 0;
	}

	@Override
	public void updateCard(Order order) {
	}

}
