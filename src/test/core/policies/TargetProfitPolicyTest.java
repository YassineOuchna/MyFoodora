package test.core.policies;

import core.policies.TargetProfitPolicy;
import core.policies.TargetProfitServiceFee;
import core.MyFoodora;
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

    @BeforeEach
    public void setUp() {
        manager = new Manager("admin", "password");
        
        // test values
        targetProfit = 5000;
        // app has 0 orders for these tests
        totalIncome = 0;
        serviceFee = 10;
        markupPercentage = 0.20;
        deliveryCost = 5;
    }

    @Test
    public void testCalculateDeliveryCost() {
    	policy = new TargetProfitDeliveryCost();
        app.setMarkupPercentage(markupPercentage);
        app.setServiceFee(serviceFee);

        double expectedDeliveryCost = (totalIncome - targetProfit - serviceFee) / (1 + markupPercentage);
        // computes and sets the corresponding deliveryCost
        double actualDeliveryCost = policy.meetTargetProfit(targetProfit);

        assertEquals(expectedDeliveryCost, actualDeliveryCost, 0.001);
    }

    @Test
    public void testCalculateServiceFee() {
    	policy = new TargetProfitServiceFee();
        app.setDeliveryCost(deliveryCost);
        app.setMarkupPercentage(markupPercentage);

        double expectedServiceFee = totalIncome - targetProfit - (markupPercentage * deliveryCost) - deliveryCost;
        double actualServiceFee= policy.meetTargetProfit(targetProfit);
        assertEquals(expectedServiceFee, actualServiceFee, 0.001);
    }

    @Test
    public void testCalculateMarkup() {
    	policy = new TargetProfitMarkupPercentage();
    	app.setDeliveryCost(deliveryCost);
    	app.setServiceFee(serviceFee);

        double expectedMarkup = (totalIncome - targetProfit - serviceFee - deliveryCost) / deliveryCost;
        double actualMarkup = policy.meetTargetProfit(targetProfit);

        assertEquals(expectedMarkup, actualMarkup, 0.001);
    }

    @Test
    public void testCalculateWithoutPolicy() {
    	app.setProfitPolicy(null);


        assertThrows(IllegalStateException.class, () -> manager.meetTargetProfit(targetProfit));
    }
}
