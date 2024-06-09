package test.core.policies;

import core.policies.TargetProfitPolicy;
import core.policies.TargetProfitServiceFee;
import core.MyFoodora;
import core.exceptions.ProfitUnreachableException;
import core.policies.TargetProfitDeliveryCost;
import core.policies.TargetProfitMarkupPercentage;
import core.users.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TargetProfitPolicyTest {
	MyFoodora app = MyFoodora.getInstance();
    Manager manager;
    TargetProfitPolicy policy;
    double targetProfit;
    double serviceFee;
    double markupPercentage;
    double deliveryCost;
    double totalIncome;
    double numberOfOrders;

    @BeforeEach
    public void setUp() {
        manager = new Manager("admin", "password");
        
        // test values
        targetProfit = 5000;
        totalIncome = 69;
        serviceFee = 10;
        markupPercentage = 0.20;
        deliveryCost = 5;
        numberOfOrders = 3;
    }

    @Test
    public void testCalculateDeliveryCost() throws ProfitUnreachableException{
    	policy = new TargetProfitDeliveryCost();
        app.setMarkupPercentage(markupPercentage);
        app.setServiceFee(serviceFee);

        double expectedDeliveryCost = - (targetProfit - totalIncome*markupPercentage)/numberOfOrders + serviceFee; 
        // computes and sets the corresponding deliveryCost
        double actualDeliveryCost = policy.meetTargetProfit(targetProfit);

        assertEquals(expectedDeliveryCost, actualDeliveryCost, 0.001);
    }

    @Test
    public void testCalculateServiceFee()throws ProfitUnreachableException {
    	policy = new TargetProfitServiceFee();
        app.setDeliveryCost(deliveryCost);
        app.setMarkupPercentage(markupPercentage);

        double expectedServiceFee = (targetProfit - totalIncome*markupPercentage)/numberOfOrders + deliveryCost;         double actualServiceFee= policy.meetTargetProfit(targetProfit);
        assertEquals(expectedServiceFee, actualServiceFee, 0.001);
    }

    @Test
    public void testCalculateMarkup() throws ProfitUnreachableException{
    	policy = new TargetProfitMarkupPercentage();
    	app.setDeliveryCost(deliveryCost);
    	app.setServiceFee(serviceFee);

        double expectedMarkup = (targetProfit - numberOfOrders*(serviceFee - deliveryCost))/totalIncome; 
        double actualMarkup = policy.meetTargetProfit(targetProfit);

        assertEquals(expectedMarkup, actualMarkup, 0.001);
    }

    @Test
    public void testProfitUnreachable() {
    	app.setProfitPolicy(null);

    	totalIncome = 0;

        assertThrows(ProfitUnreachableException.class, () -> manager.meetTargetProfit(targetProfit));
    }
    @Test
    public void testCalculateWithoutPolicy() {
    	app.setProfitPolicy(null);


        assertThrows(IllegalStateException.class, () -> manager.meetTargetProfit(targetProfit));
    }
}
