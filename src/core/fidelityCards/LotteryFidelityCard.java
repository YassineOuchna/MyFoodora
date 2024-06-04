package core.fidelityCards;

import java.util.Random;

import core.orders.Order;

public class LotteryFidelityCard extends FidelityCard{
	
	private boolean lotteryWon = false;

	/**
	 * Each card has a probability of 1 in a million
	 * chance to have free meals forever.
	 */
	public LotteryFidelityCard() {
		Random randomGenerator = new Random();
		lotteryWon = (randomGenerator.nextInt(1000000) == 5);
	}
	@Override
	public void updateCard(Order order) {
		if (lotteryWon) {
			fidelityDiscount = 1;
		}
	}

}
