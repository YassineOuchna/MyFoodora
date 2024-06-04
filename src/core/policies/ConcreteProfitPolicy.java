package core.policies;

public class ConcreteProfitPolicy implements TargetProfitPolicy {
    @Override
    public double computeDeliveryCost(double targetProfit, int totalIncome, double serviceFee, double markup) {
        // Target Profit = Total Income - (Service Fee + Markup * Delivery Cost + Delivery Cost)
        // Finding Delivery Cost
        double deliveryCost = (totalIncome - targetProfit - serviceFee) / (1 + markup);
        return deliveryCost;
    }

    @Override
    public double computeServiceFee(double targetProfit, int totalIncome, double deliveryCost, double markup) {
        // Target Profit = Total Income - (Service Fee + Markup * Delivery Cost + Delivery Cost)
        // Finding Service Fee
        double serviceFee = totalIncome - targetProfit - (markup * deliveryCost) - deliveryCost;
        return serviceFee;
    }

    @Override
    public double computeMarkup(double targetProfit, int totalIncome, double deliveryCost, double serviceFee) {
        // Target Profit = Total Income - (Service Fee + Markup * Delivery Cost + Delivery Cost)
        // Finding MarkupPercentage
        double markup = (totalIncome - targetProfit - serviceFee - deliveryCost) / deliveryCost;
        return markup;
    }
}
