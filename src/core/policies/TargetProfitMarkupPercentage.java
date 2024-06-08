package core.policies;

import java.util.Date;
import java.util.Calendar;

import core.MyFoodora;

public class TargetProfitMarkupPercentage implements TargetProfitPolicy{

	@Override
	public double meetTargetProfit(double targetProfit) {
		MyFoodora myFoodora = MyFoodora.getInstance();
		// getting last month's income
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);  
        Date lastMonth = cal.getTime();
		double lastMonthIncome = myFoodora.computeTotalIncome(lastMonth, new Date());
        // Target Profit = Total Income - (Service Fee + Markup * Delivery Cost + Delivery Cost)
        // Finding MarkupPercentage
        double markup = (lastMonthIncome- targetProfit - myFoodora.getServiceFee()-
        		myFoodora.getDeliveryCost()) / myFoodora.getDeliveryCost();
        
        myFoodora.setMarkupPercentage(markup);
        return markup;
    }

	@Override
	public String toString() {
		return "TagetProfitMarkupPercentage";
	}
}
