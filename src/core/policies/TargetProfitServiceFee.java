package core.policies;

import java.util.Date;
import java.util.Calendar;

import core.MyFoodora;
import core.exceptions.ProfitUnreachableException;

public class TargetProfitServiceFee implements TargetProfitPolicy{

	@Override
	public double meetTargetProfit(double targetProfit) throws ProfitUnreachableException {
		MyFoodora myFoodora = MyFoodora.getInstance();
		// getting last month's income
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);  
        Date lastMonth = cal.getTime();
		double lastMonthIncome = myFoodora.computeTotalIncome(lastMonth, new Date());
		
        if (lastMonthIncome == 0) {
        	throw new ProfitUnreachableException("Profit Target cannot be reached");
        } else {
		
        double serviceFee = (targetProfit - lastMonthIncome*myFoodora.getMarkupPercentage())/
        		myFoodora.getCompletedOrders().size()+ myFoodora.getDeliveryCost(); 
        myFoodora.setServiceFee(serviceFee);
        return serviceFee;
        }

        
    }
	@Override
	public String toString() {
		return "TargetProfitServiceFee";
	}
	}
	