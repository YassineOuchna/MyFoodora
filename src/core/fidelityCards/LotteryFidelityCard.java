package core.fidelityCards;

import java.util.Random;

import core.orders.Order;

public class LotteryFidelityCard extends FidelityCard{
	
	private boolean lotteryWon = false;
	private int chance;


	/**
	 * Each card has a probability of 1 in a million
	 * chance to have free meals forever.
	 */
	public LotteryFidelityCard() {
		this.chance = 1000000;
		Random randomGenerator = new Random();
		lotteryWon = (randomGenerator.nextInt(chance) == Math.floor(chance/2));
	}
	/**
	 * A different constructor to specify the
	 * chance to have free meals forever.
	 */
	public LotteryFidelityCard(int chance) {
		this.chance = chance;
		Random randomGenerator = new Random();
		lotteryWon = (randomGenerator.nextInt(chance) == Math.floor(chance/2));
	}

	public int getChance() {
		return chance;
	}

	public void setChance(int chance) {
		this.chance = chance;
	}

	
	@Override
	public void updateCard(Order order) {
		if (lotteryWon) {
			fidelityDiscount = 1;
		}
	}

}
