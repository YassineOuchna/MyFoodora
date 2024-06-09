package core.policies;

import java.util.ArrayList;

import core.orders.Order;
import core.users.Courier;

public class FairOcuppationDelivery implements DeliveryPolicy{

	@Override
	public Courier assignCourrier(ArrayList<Courier> courriers, Order order) {
		Courier selectedCourier = null;
        int leastDeliveries = Integer.MAX_VALUE;

        for (Courier courier : courriers) {
        	if (courier.isOnDuty()) {
        		int deliveries = courier.getCompletedDeliveries();
                if (deliveries < leastDeliveries) {
                    leastDeliveries = deliveries;
                    selectedCourier = courier;
        	}
            
            }
        }
        return selectedCourier;
    }
	}


