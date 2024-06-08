package core.policies;

import java.util.Date;
import java.util.Calendar;

import core.MyFoodora;

public class TargetProfitDeliveryCost implements TargetProfitPolicy{


	@Override
	public double meetTargetProfit(double targetProfit) {
        // Target Profit = Total Income - (Service Fee + Markup * Delivery Cost + Delivery Cost)
        // Finding Delivery Cost
		MyFoodora myFoodora = MyFoodora.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);  
        Date lastMonth = cal.getTime();
		double lastMonthIncome = myFoodora.computeTotalIncome(lastMonth, new Date());
        double deliveryCost = (lastMonthIncome - targetProfit - myFoodora.getServiceFee()) / (1 + myFoodora.getMarkupPercentage());
        
        myFoodora.setDeliveryCost(deliveryCost);
        return deliveryCost;
	}

	@Override
	public String toString() {
		return "TargetProfitDeliveryCost";
	}
	
	

}
