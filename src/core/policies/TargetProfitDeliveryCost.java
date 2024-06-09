package core.policies;

import java.util.Date;
import java.util.Calendar;

import core.MyFoodora;
import core.exceptions.ProfitUnreachableException;

public class TargetProfitDeliveryCost implements TargetProfitPolicy{


	@Override
	public double meetTargetProfit(double targetProfit) throws ProfitUnreachableException {
		MyFoodora app = MyFoodora.getInstance();
		// Finding last month's income
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);  
        Date lastMonth = cal.getTime();
        double lastMonthIncome = app.computeTotalIncome(lastMonth, new Date());
        
        if (lastMonthIncome == 0) {
        	throw new ProfitUnreachableException("Profit Target cannot be reached");
        } else {
        	double deliveryCost = (lastMonthIncome*app.getMarkupPercentage()- targetProfit)/app.getCompletedOrders().size()+ app.getServiceFee(); 
        	app.setDeliveryCost(deliveryCost);
        	return deliveryCost;
        }
	}

	@Override
	public String toString() {
		return "TargetProfitDeliveryCost";
	}
	
	

}
