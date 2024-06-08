package core.policies;

import java.util.Date;
import java.util.Calendar;

import core.MyFoodora;

public class TargetProfitServiceFee implements TargetProfitPolicy{

	@Override
	public double meetTargetProfit(double targetProfit) {
		MyFoodora myFoodora = MyFoodora.getInstance();
		// getting last month's income
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);  
        Date lastMonth = cal.getTime();
		double lastMonthIncome = myFoodora.computeTotalIncome(lastMonth, new Date());
        // Target Profit = Total Income - (Service Fee + Markup * Delivery Cost + Delivery Cost)
        // Finding Service Fee
        double serviceFee =  lastMonthIncome - targetProfit - 
        		(myFoodora.getMarkupPercentage() * myFoodora.getDeliveryCost()) - myFoodora.getDeliveryCost();
        
        myFoodora.setServiceFee(serviceFee);
        return serviceFee;
    }
	@Override
	public String toString() {
		return "TargetProfitServiceFee";
	}
	}
	