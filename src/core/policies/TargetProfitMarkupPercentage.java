package core.policies;

import java.util.Date;
import java.util.Calendar;

import core.MyFoodora;
import core.exceptions.ProfitUnreachableException;

public class TargetProfitMarkupPercentage implements TargetProfitPolicy{

	@Override
	public double meetTargetProfit(double targetProfit) throws ProfitUnreachableException {
		MyFoodora myFoodora = MyFoodora.getInstance();
		// getting last month's income
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);  
        Date lastMonth = cal.getTime();
		double lastMonthIncome = myFoodora.computeTotalIncome(lastMonth, new Date());
        // Target Profit = Total Income - (Service Fee + Markup * Delivery Cost + Delivery Cost)
        // Finding MarkupPercentage
        if (lastMonthIncome == 0) {
        	throw new ProfitUnreachableException("Profit Target cannot be reached");
        } else {
        double markup = (targetProfit - myFoodora.getCompletedOrders().size()*
        		(myFoodora.getServiceFee() - myFoodora.getDeliveryCost()))/lastMonthIncome; 
        myFoodora.setMarkupPercentage(markup);
        return markup;
        }
       
    }

	@Override
	public String toString() {
		return "TagetProfitMarkupPercentage";
	}
}
