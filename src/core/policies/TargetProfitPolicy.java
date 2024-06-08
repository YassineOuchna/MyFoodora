package core.policies;

public interface TargetProfitPolicy {
	
	/**
	 * Computes the delivery cost or 
	 * the service fee or the markup percentage needed
	 * to meet a target profit this month and sets it up.
	 * @param targetProfit : target profit for this month. 
	 * @return a double representing either the delivery fee,
	 * service fee or the markup percentage depending on the policy.
	 */
	public double meetTargetProfit(double targetProfit);
	
}
