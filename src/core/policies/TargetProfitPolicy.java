package core.policies;

public interface TargetProfitPolicy {
	public double computeDeliveryCost(double targetProfit, int totalIncome, double serviceFee, double markup);
	public double computeServiceFee(double targetProfit, int totalIncome, double deliveryCost, double markup);
	public double computeMarkup(double targetProfit, int totalIncome, double deliveryCost, double serviceFee);
}
