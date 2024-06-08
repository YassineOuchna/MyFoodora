package test.core.policies;

import core.policies.TargetProfitPolicy;
import core.MyFoodora;
import core.policies.ConcreteProfitPolicy;
import core.users.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TargetProfitPolicyTest {
    Manager manager;
    TargetProfitPolicy policy;

    @BeforeEach
    public void setUp() {
        policy = new ConcreteProfitPolicy();
        manager = new Manager("admin", "password");
        manager.setProfitPolicy(policy);
    }

    @Test
    public void testCalculateDeliveryCost() {
        double targetProfit = 5000;
        int totalIncome = 10000;
        double serviceFee = 10;
        double markupPercentage = 0.20;

        double expectedDeliveryCost = (totalIncome - targetProfit - serviceFee) / (1 + markupPercentage);
        double actualDeliveryCost = manager.computeDeliveryCost(targetProfit, totalIncome, serviceFee, markupPercentage);

        assertEquals(expectedDeliveryCost, actualDeliveryCost, 0.001);
    }

    @Test
    public void testCalculateServiceFee() {
        double targetProfit = 5000;
        int totalIncome = 10000;
        double deliveryCost = 5;
        double markupPercentage = 0.20;

        double expectedServiceFee = totalIncome - targetProfit - (markupPercentage * deliveryCost) - deliveryCost;
        double actualServiceFee = manager.computeServiceFee(targetProfit, totalIncome, deliveryCost, markupPercentage);

        assertEquals(expectedServiceFee, actualServiceFee, 0.001);
    }

    @Test
    public void testCalculateMarkup() {
        double targetProfit = 5000;
        int totalIncome = 10000;
        double deliveryCost = 5;
        double serviceFee = 10;

        double expectedMarkup = (totalIncome - targetProfit - serviceFee - deliveryCost) / deliveryCost;
        double actualMarkup = manager.computeMarkup(targetProfit, totalIncome, deliveryCost, serviceFee);

        assertEquals(expectedMarkup, actualMarkup, 0.001);
    }

    @Test
    public void testCalculateWithoutPolicy() {
    	MyFoodora.setProfitPolicy(null);

        double targetProfit = 5000;
        int totalIncome = 10000;
        double serviceFee = 10;
        double markupPercentage = 0.20;
        double deliveryCost = 5;

        assertThrows(IllegalStateException.class, () -> manager.computeDeliveryCost(targetProfit, totalIncome, serviceFee, markupPercentage));
        assertThrows(IllegalStateException.class, () -> manager.computeServiceFee(targetProfit, totalIncome, deliveryCost, markupPercentage));
        assertThrows(IllegalStateException.class, () -> manager.computeMarkup(targetProfit, totalIncome, deliveryCost, serviceFee));
    }
}
